package com.data.generator.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

import com.data.generator.constants.CommonConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.file.impl.CsvWriter;
import com.data.generator.util.Precondition;
import com.data.generator.util.SparqlUtil;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class DbpediaDataGenerator extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Provide an unique id (Mostly your DB user name).By using this unique id I
	 * will create a temp file to write the content. I will use this temp file
	 * while inserting the data into a DB.
	 */
	private final String directoryName;
	/**
	 * Provide a SPARQL Query for fetching the records.
	 */
	private final String query;
	/**
	 * Provide an end-point of a service.
	 */
	private String endPoint;
	private String outputFileName;
	private final List<String> columnNamesList;

	private CsvWriter writer;

	public DbpediaDataGenerator(String argDirectoryName, String argQuery) {
		super();
		this.directoryName = argDirectoryName;
		this.query = argQuery;
		this.columnNamesList = new ArrayList<String>();
	}

	public DbpediaDataGenerator(String argUniqueId, String argQuery,
			String argEndPoint) {
		super();
		this.directoryName = argUniqueId;
		this.query = argQuery;
		this.endPoint = argEndPoint;
		this.columnNamesList = new ArrayList<String>();
	}

	public void generate() {
		ResultSet resultSet = SparqlUtil.generate(this.getEndPoint(),
				this.getQuery());
		Map<String[], List<List<String>>> headerNamesVsTokensListMap = new HashMap<String[], List<List<String>>>();
		if (Precondition.checkNotNull(resultSet)) {
			File file = new File(this.getDirectoryName());
			if (!file.exists()) {
				file.mkdir();
			}
			List<List<String>> valuesList = new ArrayList<List<String>>();
			while (resultSet.hasNext()) {
				QuerySolution querySolution = resultSet.next();
				collectColumnNames(querySolution);
				if (Precondition.checkNotEmpty(this.getColumnNamesList())) {
					List<String> tokensList = new ArrayList<String>();
					for (String columnName : this.getColumnNamesList()) {
						RDFNode rdfNode = querySolution.get(columnName);
						if (rdfNode.isLiteral()) {
							String value = rdfNode.asNode().getLiteral()
									.getValue().toString();
							tokensList.add(value);
						}
					}
					valuesList.add(tokensList);
				}
			}
			headerNamesVsTokensListMap.put(
					this.getColumnNamesList().toArray(
							new String[this.getColumnNamesList().size()]),
					valuesList);
			write(headerNamesVsTokensListMap);
		}
	}

	private void write(
			Map<String[], List<List<String>>> argHeaderNamesVsTokensListMap) {
		String outputFileName = prepareOutputFile();
		this.writer = new CsvWriter(outputFileName);
		this.writer.write(argHeaderNamesVsTokensListMap);
	}

	private String prepareOutputFile() {
		return new StringBuilder().append(KeyBoardConstants.DOT)
				.append(KeyBoardConstants.FORWARD_SLASH)
				.append(this.getDirectoryName())
				.append(KeyBoardConstants.FORWARD_SLASH)
				.append(this.getOutputFileName()).toString();
	}

	private void collectColumnNames(QuerySolution argQuerySolution) {
		if (Precondition.checkNotNull(argQuerySolution)
				&& Precondition.checkEmpty(this.getColumnNamesList())) {
			Iterator<String> columnIterator = argQuerySolution.varNames();
			while (columnIterator.hasNext()) {
				String columnName = columnIterator.next();
				this.getColumnNamesList().add(columnName);
			}
		}
	}

	public String getDirectoryName() {
		return this.directoryName;
	}

	public String getQuery() {
		return this.query;
	}

	public String getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(String argEndPoint) {
		this.endPoint = argEndPoint;
	}

	public String getOutputFileName() {
		return this.outputFileName;
	}

	public void setOutputFileName(String argOutputFileName) {
		this.outputFileName = argOutputFileName;
	}

	public List<String> getColumnNamesList() {
		return this.columnNamesList;
	}

	@Override
	protected void compute() {
		generate();
	}
}
