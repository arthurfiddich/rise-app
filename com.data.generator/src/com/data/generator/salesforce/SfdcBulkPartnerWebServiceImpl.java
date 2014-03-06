package com.data.generator.salesforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.CommonConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.constants.SalesforceConstants;
import com.data.generator.exceptions.BulkDataException;
import com.data.generator.exceptions.PartnerConnectionException;
import com.data.generator.util.Precondition;
import com.sforce.async.AsyncApiException;
import com.sforce.async.BatchInfo;
import com.sforce.async.BatchStateEnum;
import com.sforce.async.BulkConnection;
import com.sforce.async.CSVReader;
import com.sforce.async.ContentType;
import com.sforce.async.JobInfo;
import com.sforce.async.JobStateEnum;
import com.sforce.async.OperationEnum;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SfdcBulkPartnerWebServiceImpl extends SfdcPartnerWebServiceImpl {

	private static Logger logger = LoggerFactory
			.getLogger(SfdcBulkPartnerWebServiceImpl.class);

	private BulkConnection bulkConnection;
	private String sObjectType;
	private String sampleFileName;
	private String operationType;

	public SfdcBulkPartnerWebServiceImpl(String argSessionId,
			String argServerUrl) {
		super(argSessionId, argServerUrl);
	}

	public SfdcBulkPartnerWebServiceImpl(String argUserName,
			String argPassword, String argSecurityToken, String argAuthUrl) {
		super(argUserName, argPassword, argSecurityToken, argAuthUrl);
	}

	@Override
	public void initialize() {
		initializeBinding();
	}

	public void execute() {
		JobInfo job = null;
		try {
			job = createJob(this.getsObjectType(), this.getBulkConnection(),
					this.getOperationType());
			List<BatchInfo> batchInfoList = createBatchesFromCSVFile(
					this.getBulkConnection(), job, this.getSampleFileName());
			closeJob(this.getBulkConnection(), job.getId());
			awaitCompletion(this.getBulkConnection(), job, batchInfoList);
			checkResults(this.getBulkConnection(), job, batchInfoList);
		} catch (AsyncApiException e) {
			throw new BulkDataException("Exception while creating a job: ", e);
		} catch (IOException e) {
			throw new BulkDataException(
					"Exception while checking the results: ", e);
		}
	}

	@Override
	protected void initializeBinding() {
		try {
			logger.debug("Bulk API Connection initialization started...");
			ConnectorConfig partnerConfig = new ConnectorConfig();
			partnerConfig.setUsername(this.getUserName());
			partnerConfig.setPassword(this.getPasswordWithSecurityToken());
			partnerConfig
					.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/28.0");
			// Creating the connection automatically handles login and stores
			// the session in partnerConfig
			PartnerConnection partnerConnection = new PartnerConnection(
					partnerConfig);
			// When PartnerConnection is instantiated, a login is implicitly
			// executed and, if successful, a valid session is stored in the
			// ConnectorConfig instance. Use this key to initialize a
			// BulkConnection:
			ConnectorConfig config = new ConnectorConfig();
			config.setSessionId(partnerConfig.getSessionId());
			// The endpoint for the Bulk API service is the same as for the
			// normal SOAP uri until the /Soap/ part. From here it's
			// '/async/versionNumber'
			String soapEndpoint = partnerConfig.getServiceEndpoint();
			String apiVersion = getApiVersion(soapEndpoint);
			if (Precondition.checkEmpty(apiVersion)) {
				apiVersion = SalesforceConstants.CURRENT_USED_VERSION;
			}
			String restEndpoint = prepateRestEndPoint(soapEndpoint, apiVersion);
			Precondition.ensureNotEmpty(restEndpoint, "Rest End Point");
			config.setRestEndpoint(restEndpoint);
			// This should only be false when doing debugging.
			config.setCompression(true);
			// Set this to true to see HTTP requests and responses on stdout
			config.setTraceMessage(true);
			this.bulkConnection = new BulkConnection(config);
		} catch (AsyncApiException e) {
			throw new BulkDataException(
					"Exception while establishing a BulkConnection with the salesforce: ",
					e);
		} catch (ConnectionException e) {
			throw new PartnerConnectionException(
					"Exception while creating a PartnerConnection: ", e);
		}
	}

	protected String prepateRestEndPoint(String argEndPoint,
			String argApiVersion) {
		logger.debug("Preparing a rest end point for establishing a BulkConnection...");
		if (Precondition.checkNotEmpty(argEndPoint)
				&& Precondition.checkNotEmpty(argApiVersion)) {
			StringBuilder restEndPointBuilder = new StringBuilder();
			String soapToken = SalesforceConstants.SOAP_TOKEN
					+ KeyBoardConstants.FORWARD_SLASH;
			String endPoint = argEndPoint.substring(0,
					argEndPoint.indexOf(soapToken));
			restEndPointBuilder.append(endPoint);
			restEndPointBuilder.append(SalesforceConstants.ASYNC);
			restEndPointBuilder.append(KeyBoardConstants.FORWARD_SLASH);
			restEndPointBuilder.append(argApiVersion);
			return restEndPointBuilder.toString();
		}
		return null;
	}

	protected String getApiVersion(String argSoapEndpoint) {
		logger.debug("Getting an API version based on the SOAP End Point..."
				+ argSoapEndpoint);
		if (Precondition.checkNotEmpty(argSoapEndpoint)) {
			int forwardSlashLastIndex = argSoapEndpoint
					.indexOf(CommonConstants.U_SLASH);
			if (Precondition.checkNonNegative(forwardSlashLastIndex)) {
				String soapApi = argSoapEndpoint
						.substring(forwardSlashLastIndex
								+ CommonConstants.U_SLASH.length());
				int apiVersionIndex = soapApi
						.indexOf(KeyBoardConstants.FORWARD_SLASH);
				if (Precondition.checkNonNegative(apiVersionIndex)) {
					return soapApi.substring(0, apiVersionIndex);
				}
			}
		}
		return SalesforceConstants.EMPTY;
	}

	/**
	 * Create a new job using the Bulk API.
	 * 
	 * @param argSobjectType
	 *            The object type being loaded, such as "Account"
	 * @param argBulkConnection
	 *            BulkConnection used to create the new job.
	 * @return The JobInfo for the new job.
	 * @throws AsyncApiException
	 */
	protected JobInfo createJob(String argSobjectType,
			BulkConnection argBulkConnection, String argOperation)
			throws AsyncApiException {
		logger.debug("Creating a job based on the operation...");
		Precondition.ensureNotEmpty(argSobjectType, "SObject Type");
		Precondition.ensureNotNull(argBulkConnection, "Bulk Connection");
		Precondition.ensureNotEmpty(argOperation, "Operation Type");
		JobInfo job = new JobInfo();
		job.setObject(argSobjectType);
		OperationEnum operationEnum = OperationEnum.valueOf(argOperation);
		if (Precondition.checkNotNull(operationEnum)) {
			job.setOperation(operationEnum);
		}
		job.setContentType(ContentType.CSV);
		job = argBulkConnection.createJob(job);
		logger.debug("Job: " + job);
		return job;
	}

	public BulkConnection getBulkConnection() {
		return this.bulkConnection;
	}

	public void setBulkConnection(BulkConnection argBulkConnection) {
		this.bulkConnection = argBulkConnection;
	}

	public String getsObjectType() {
		return this.sObjectType;
	}

	public void setsObjectType(String argSObjectType) {
		this.sObjectType = argSObjectType;
	}

	public String getSampleFileName() {
		return this.sampleFileName;
	}

	public void setSampleFileName(String argSampleFileName) {
		this.sampleFileName = argSampleFileName;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String argOperationType) {
		this.operationType = argOperationType;
	}

	/**
	 * Create and upload batches using a CSV file. The file into the appropriate
	 * size batch files.
	 * 
	 * @param argBulkConnection
	 *            Connection to use for creating batches
	 * @param argJobInfo
	 *            Job associated with new batches
	 * @param argCsvFileName
	 *            The source file for batch data
	 */
	protected List<BatchInfo> createBatchesFromCSVFile(
			BulkConnection argBulkConnection, JobInfo argJobInfo,
			String argCsvFileName) {
		Precondition.ensureNotEmpty(argCsvFileName, "CSV FileName");
		Precondition.ensureNotNull(argBulkConnection, "Bulk Connection");
		Precondition.ensureNotNull(argJobInfo, "Job Info");
		File tempFile = null;
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		List<BatchInfo> batchInfos = new ArrayList<BatchInfo>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(argCsvFileName)));
			// read the CSV header row
			byte[] headerBytes = (bufferedReader.readLine() + KeyBoardConstants.NEW_LINE)
					.getBytes(CommonConstants.UTF_8);
			int headerBytesLength = headerBytes.length;
			File file = new File(CommonConstants.TMEP_DIRECTORY_NAME);
			tempFile = File.createTempFile("temp",
					CommonConstants.CSV_EXTENSION, file);

			// Split the CSV file into multiple batches
			fileOutputStream = new FileOutputStream(tempFile);
			// 10 million bytes per batch
			int maxBytesPerBatch = SalesforceConstants.MAX_BYTES_PER_BATCH;
			// 10 thousand rows per batch
			int maxRowsPerBatch = SalesforceConstants.MAX_ROWS_PER_BATCH;
			int currentBytes = 0;
			int currentLines = 0;
			String nextLine;
			while ((nextLine = bufferedReader.readLine()) != null) {
				byte[] bytes = (nextLine + KeyBoardConstants.NEW_LINE)
						.getBytes(CommonConstants.UTF_8);
				// Create a new batch when our batch size limit is reached
				if (currentBytes + bytes.length > maxBytesPerBatch
						|| currentLines > maxRowsPerBatch) {
					BatchInfo batchInfo = createBatch(tempFile,
							argBulkConnection, argJobInfo);
					if (Precondition.checkNotNull(batchInfo)) {
						batchInfos.add(batchInfo);
					}
					closeOutputStrem(fileOutputStream);
					currentBytes = 0;
					currentLines = 0;
				}
				if (currentBytes == 0) {
					fileOutputStream = new FileOutputStream(tempFile);
					fileOutputStream.write(headerBytes);
					currentBytes = headerBytesLength;
					currentLines = 1;
				}
				fileOutputStream.write(bytes);
				currentBytes += bytes.length;
				currentLines++;
			}
			// Finished processing all rows
			// Create a final batch for any remaining data
			if (currentLines > 1) {
				BatchInfo batchInfo = createBatch(tempFile, argBulkConnection,
						argJobInfo);
				if (Precondition.checkNotNull(batchInfo)) {
					batchInfos.add(batchInfo);
				}
				closeOutputStrem(fileOutputStream);
			}
		} catch (Exception e) {
			throw new BulkDataException(
					"Exception while creating a job batches: ", e);
		} finally {
			if (Precondition.checkNotNull(tempFile)) {
				tempFile.delete();
			}
			if (Precondition.checkNotNull(bufferedReader)) {
				try {
					bufferedReader.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
			if (Precondition.checkNotNull(fileOutputStream)) {
				try {
					fileOutputStream.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
		return batchInfos;
	}

	protected void closeOutputStrem(FileOutputStream argFileOutputStream) {
		if (Precondition.checkNotNull(argFileOutputStream)) {
			try {
				argFileOutputStream.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	/**
	 * Create a batch by uploading the contents of the file. This closes the
	 * output stream.
	 * 
	 * @param argTempFile
	 *            The file associated with the above stream.
	 * @param argBulkConnection
	 *            The BulkConnection used to create the new batch.
	 * @param argJobInfo
	 *            The JobInfo associated with the new batch.
	 * @return BatchInfo
	 */
	private BatchInfo createBatch(File argTempFile,
			BulkConnection argBulkConnection, JobInfo argJobInfo) {
		FileInputStream tmpInputStream = null;
		try {
			tmpInputStream = new FileInputStream(argTempFile);
			BatchInfo batchInfo = argBulkConnection.createBatchFromStream(
					argJobInfo, tmpInputStream);
			logger.debug("Batch Info: " + batchInfo);
			return batchInfo;
		} catch (Exception e) {
			throw new BulkDataException(
					"Exception while creating a batch for the job: ", e);
		} finally {
			if (Precondition.checkNotNull(tmpInputStream)) {
				try {
					tmpInputStream.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
	}

	protected void closeJob(BulkConnection argBulkConnection, String argJobId) {
		Precondition.ensureNotNull(argBulkConnection, "Bulk Connection");
		Precondition.ensureNotNull(argJobId, "Job ID");
		JobInfo job = new JobInfo();
		job.setId(argJobId);
		job.setState(JobStateEnum.Closed);
		try {
			argBulkConnection.updateJob(job);
		} catch (AsyncApiException e) {
			throw new BulkDataException(
					"Exception while closing/updating the job");
		}
	}

	/**
	 * Wait for a job to complete by polling the Bulk API.
	 * 
	 * @param argBulkConnection
	 *            BulkConnection used to check results.
	 * @param argJobInfo
	 *            The job awaiting completion.
	 * @param argBatchInfoList
	 *            List of batches for this job.
	 * @throws AsyncApiException
	 */
	protected void awaitCompletion(BulkConnection argBulkConnection,
			JobInfo argJobInfo, List<BatchInfo> argBatchInfoList) {
		Precondition.ensureNotNull(argBulkConnection, "Bulk Connection");
		Precondition.ensureNotNull(argJobInfo, "Job Info");
		Precondition.ensureNotNull(argBatchInfoList, "Batch Info List");
		long sleepTime = 0L;
		Set<String> incomplete = new HashSet<String>();
		for (BatchInfo bi : argBatchInfoList) {
			incomplete.add(bi.getId());
		}
		while (!incomplete.isEmpty()) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// ignore
			}
			logger.debug("Awaiting results..." + incomplete.size());
			sleepTime = 10000L;
			BatchInfo[] statusList;
			try {
				statusList = argBulkConnection.getBatchInfoList(
						argJobInfo.getId()).getBatchInfo();
				for (BatchInfo b : statusList) {
					if (b.getState() == BatchStateEnum.Completed
							|| b.getState() == BatchStateEnum.Failed) {
						if (incomplete.remove(b.getId())) {
							logger.debug("BATCH STATUS:\n" + b);
						}
					}
				}
			} catch (AsyncApiException e) {
				throw new BulkDataException(
						"Exception while checking the status of a BatchInfoList: ",
						e);
			}
		}
	}

	/**
	 * Gets the results of the operation and checks for errors.
	 */
	protected void checkResults(BulkConnection argBulkConnection,
			JobInfo argJobInfo, List<BatchInfo> argBatchInfoList)
			throws AsyncApiException, IOException {
		Precondition.ensureNotNull(argBulkConnection, "Bulk Connection");
		Precondition.ensureNotNull(argJobInfo, "Job Info");
		Precondition.ensureNotNull(argBatchInfoList, "Batch Info List");
		// batchInfoList was populated when batches were created and submitted
		for (BatchInfo b : argBatchInfoList) {
			CSVReader rdr = new CSVReader(
					argBulkConnection.getBatchResultStream(argJobInfo.getId(),
							b.getId()));
			List<String> resultHeader = rdr.nextRecord();
			int resultCols = resultHeader.size();

			List<String> row;
			while ((row = rdr.nextRecord()) != null) {
				Map<String, String> resultInfo = new HashMap<String, String>();
				for (int i = 0; i < resultCols; i++) {
					resultInfo.put(resultHeader.get(i), row.get(i));
				}
				boolean success = Boolean.valueOf(resultInfo.get("Success"));
				boolean created = Boolean.valueOf(resultInfo.get("Created"));
				String id = resultInfo.get("Id");
				String error = resultInfo.get("Error");
				if (success && created) {
					logger.debug("Created row with id " + id);
				} else if (!success) {
					logger.debug("Failed with error: " + error);
				}
			}
		}
	}
}
