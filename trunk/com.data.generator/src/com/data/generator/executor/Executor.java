package com.data.generator.executor;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.CommonConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.constants.SparqlConstants;
import com.data.generator.impl.DbpediaDataGenerator;
import com.data.generator.util.Precondition;

public class Executor {

	private static Logger logger = LoggerFactory.getLogger(Executor.class);

	/** The Constant DEFAULT_THREAD_POOL_SIZE. */
	private static final int DEFAULT_THREAD_POOL_SIZE = 8;

	/** The Constant DEFAULT_QUEUE_SIZE. */
	private static final int DEFAULT_QUEUE_SIZE = 200;

	/** The Constant DEFAULT_KEEP_ALIVE. */
	private static final long DEFAULT_KEEP_ALIVE = 3;

	/** The Constant DEFAULT_WAIT_TIME_TO_QUEUE. */
	private static final long DEFAULT_WAIT_TIME_TO_QUEUE = 1 * 1000L;

	private long timeout;
	private int threads;
	private int queueSize;
	private ExecutorService executorService;
	private double numberOfRecords;
	private String fileExtension;
	/**
	 * Provide an unique id (Mostly your DB user name).By using this unique id I
	 * will create a temp file to write the content. I will use this temp file
	 * while inserting the data into a DB.
	 */
	private final String directoryName;

	public Executor(String argDirectoryName) {
		super();
		this.directoryName = argDirectoryName;
	}

	public void initialize() {
		if (Precondition.checkZero(this.getThreads())) {
			this.setThreads(DEFAULT_THREAD_POOL_SIZE);
		}

		if (Precondition.checkZero(this.getQueueSize())) {
			this.setQueueSize(DEFAULT_QUEUE_SIZE);
		}
		if (Precondition.checkZero(this.getNumberOfRecords())) {
			this.setNumberOfRecords(SparqlConstants.DEFAULT_LIMIT);
		}
		if (Precondition.checkNull(this.getFileExtension())) {
			this.setFileExtension(SparqlConstants.DEFAULT_FILE_EXTENSION);
		}
		this.setExecutorService(createExecutorService());
	}

	private ExecutorService createExecutorService() {
		BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingDeque<Runnable>(
				this.getThreads());
		ThreadPoolExecutor threadPoolExecutor = new DataGeneratorThreadPoolExecutor(
				this.getThreads(), this.getThreads(), DEFAULT_KEEP_ALIVE,
				TimeUnit.SECONDS, linkedBlockingQueue);
		return threadPoolExecutor;
	}

	public void generate() {
		logger.info("Generate method executing in " + this.getClass().getName());
		try {
			File file = new File(this.getDirectoryName());
			if (!file.exists()) {
				file.mkdir();
			}
			double numberOfIterations = Math.ceil(this.getNumberOfRecords()
					/ SparqlConstants.DEFAULT_BATCH_SIZE);
			int offset = 0;
			for (int i = 0; i < numberOfIterations; i++) {
				String query = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> PREFIX owl:<http://dbpedia.org/ontology/> select DISTINCT ?name ?description where { ?x a owl:Person; rdfs:label ?name; owl:abstract ?description. FILTER(LANG(?name) = \"en\") FILTER(LANG(?description) = \"en\") } LIMIT"
						+ KeyBoardConstants.SPACE
						+ SparqlConstants.DEFAULT_BATCH_SIZE
						+ KeyBoardConstants.SPACE
						+ SparqlConstants.OFF_SET
						+ KeyBoardConstants.SPACE + offset;
				DbpediaDataGenerator dbpediaDataGenerator = new DbpediaDataGenerator(
						this.getDirectoryName(), query,
						SparqlConstants.SPARQL_ENDPOINT);
				String outputFileName = prepareOutputFileName(i + 1);
				dbpediaDataGenerator.setOutputFileName(outputFileName);
				this.execute(dbpediaDataGenerator);
				offset += SparqlConstants.DEFAULT_BATCH_SIZE;
			}
		} finally {
			this.getExecutorService().shutdown();
		}
	}

	private String prepareOutputFileName(int argCount) {
		return new StringBuilder().append(this.getDirectoryName())
				.append(KeyBoardConstants.HYPHEN).append(argCount)
				.append(KeyBoardConstants.DOT).append(this.getFileExtension())
				.toString();
	}

	protected void execute(DbpediaDataGenerator argDbpediaDataGenerator) {
		logger.info("Executor service is executing/delegating the task...");
		boolean queued = false;
		do {
			try {
				this.getExecutorService().execute(argDbpediaDataGenerator);
				queued = true;
			} catch (RejectedExecutionException e) {
				try {
					Thread.sleep(DEFAULT_WAIT_TIME_TO_QUEUE);
				} catch (InterruptedException ignore) {
					// ignore
				}
			}
		} while (!queued);
	}

	public ExecutorService getExecutorService() {
		return this.executorService;
	}

	public void setExecutorService(ExecutorService argExecutorService) {
		this.executorService = argExecutorService;
	}

	public long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(long argTimeout) {
		this.timeout = argTimeout;
	}

	public int getThreads() {
		return this.threads;
	}

	public void setThreads(int argThreads) {
		this.threads = argThreads;
	}

	public int getQueueSize() {
		return this.queueSize;
	}

	public void setQueueSize(int argQueueSize) {
		this.queueSize = argQueueSize;
	}

	public double getNumberOfRecords() {
		return this.numberOfRecords;
	}

	public void setNumberOfRecords(double argNumberOfRecords) {
		this.numberOfRecords = argNumberOfRecords;
	}

	public String getDirectoryName() {
		return this.directoryName;
	}

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String argFileExtension) {
		this.fileExtension = argFileExtension;
	}

}
