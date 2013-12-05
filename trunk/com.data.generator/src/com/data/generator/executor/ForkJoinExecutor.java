package com.data.generator.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.CommonConstants;
import com.data.generator.constants.SparqlConstants;
import com.data.generator.util.Precondition;

public class ForkJoinExecutor extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(ForkJoinExecutor.class);

	private double numberOfRecords;
	private String fileExtension;
	private ForkJoinPool forkJoinPool;
	private int maxThreads;
	/**
	 * Provide an unique id (Mostly your DB user name).By using this unique id I
	 * will create a temp file to write the content. I will use this temp file
	 * while inserting the data into a DB.
	 */
	private final String directoryName;

	public ForkJoinExecutor(int argMaxThreads, String argDirectoryName) {
		super();
		this.maxThreads = argMaxThreads;
		this.directoryName = argDirectoryName;
	}

	public void initialize() {
		if (Precondition.checkZero(this.getNumberOfRecords())) {
			this.setNumberOfRecords(SparqlConstants.DEFAULT_LIMIT);
		}
		if (Precondition.checkNull(this.getFileExtension())) {
			this.setFileExtension(SparqlConstants.DEFAULT_FILE_EXTENSION);
		}
		if (this.getMaxThreads() > -1
				&& Precondition.checkZero(this.getMaxThreads())) {
			this.setMaxThreads(SparqlConstants.DEFAULT_THREAD_SIZE);
		}
		createForkJoinPool();
	}

	private void createForkJoinPool() {
		this.forkJoinPool = new ForkJoinPool(this.getMaxThreads());
	}

	@Override
	protected void compute() {
		logger.info("Generate method executing in " + this.getClass().getName());
		try {
			List<DbpediaDataGenerator> dbpediaDataGeneratorList = new ArrayList<DbpediaDataGenerator>();
			File file = new File(this.getDirectoryName());
			if (!file.exists()) {
				file.mkdir();
			}
			double numberOfIterations = Math.ceil(this.getNumberOfRecords()
					/ SparqlConstants.DEFAULT_BATCH_SIZE);
			int offset = 0;
			for (int i = 0; i < numberOfIterations; i++) {
				String query = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> PREFIX owl:<http://dbpedia.org/ontology/> select DISTINCT ?name ?description where { ?x a owl:Person; rdfs:label ?name; owl:abstract ?description. FILTER(LANG(?name) = \"en\") FILTER(LANG(?description) = \"en\") } LIMIT"
						+ CommonConstants.SPACE
						+ SparqlConstants.DEFAULT_BATCH_SIZE
						+ CommonConstants.SPACE
						+ SparqlConstants.OFF_SET
						+ CommonConstants.SPACE + offset;
				DbpediaDataGenerator dbpediaDataGenerator = new DbpediaDataGenerator(
						this.getDirectoryName(), query,
						SparqlConstants.SPARQL_ENDPOINT);
				String outputFileName = prepareOutputFileName(i + 1);
				dbpediaDataGenerator.setOutputFileName(outputFileName);
				dbpediaDataGeneratorList.add(dbpediaDataGenerator);
				offset += SparqlConstants.DEFAULT_BATCH_SIZE;
			}
			invokeAll(dbpediaDataGeneratorList);
		} finally {
		}
	}

	private String prepareOutputFileName(int argCount) {
		return new StringBuilder().append(this.getDirectoryName())
				.append(CommonConstants.HYPHEN).append(argCount)
				.append(CommonConstants.DOT).append(this.getFileExtension())
				.toString();
	}

	public double getNumberOfRecords() {
		return this.numberOfRecords;
	}

	public void setNumberOfRecords(double argNumberOfRecords) {
		this.numberOfRecords = argNumberOfRecords;
	}

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String argFileExtension) {
		this.fileExtension = argFileExtension;
	}

	public String getDirectoryName() {
		return this.directoryName;
	}

	public ForkJoinPool getForkJoinPool() {
		return this.forkJoinPool;
	}

	public void setForkJoinPool(ForkJoinPool argForkJoinPool) {
		this.forkJoinPool = argForkJoinPool;
	}

	public void setMaxThreads(int argMaxThreads) {
		this.maxThreads = argMaxThreads;
	}

	public int getMaxThreads() {
		return this.maxThreads;
	}

}
