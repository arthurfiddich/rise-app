package com.data.generator.executor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.data.generator.constants.SparqlConstants;
import com.data.generator.util.SparqlUtil;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class CategoryFetcher {
	public static void main(String[] args) {
		String directoryName = "./category";
		int recordCount = 753524;
		double iterations = recordCount / 50000.0;
		for (int i = 0; i < iterations; i++) {
			int offset = (i * 50000);
			String query = "PREFIX dcterms:<http://purl.org/dc/terms/subject> SELECT DISTINCT ?category WHERE {?subject dcterms:subject ?category} LIMIT 50000 Offset "
					+ offset;
			ResultSet resultSet = SparqlUtil.generate(
					SparqlConstants.SPARQL_ENDPOINT, query);
			int count = 0;
			int fileCount = 1;
			List<String> categoryList = new ArrayList<String>();
			while (resultSet.hasNext()) {
				QuerySolution querySolution = resultSet.next();
				Iterator<String> columnIterator = querySolution.varNames();
				while (columnIterator.hasNext()) {
					String columnName = columnIterator.next();
					RDFNode rdfNode = querySolution.get(columnName);
					String value = rdfNode.asNode().getLiteral().getValue()
							.toString();
					categoryList.add(value);
					++count;
				}
				if (count >= 50000) {
					BufferedWriter bufferedWriter = null;
					try {
						String filePath = createFilePath(fileCount,
								directoryName);
						bufferedWriter = new BufferedWriter(new FileWriter(
								filePath));
						write(categoryList, bufferedWriter);
						bufferedWriter.flush();
					} catch (IOException e) {
						throw new RuntimeException(
								"Exception while initializing the buffered writer: ",
								e);
					} finally {
						if (bufferedWriter != null) {
							try {
								bufferedWriter.close();
							} catch (Exception ignore) {
								// ignore
							}
						}
					}
				}
			}
		}
	}

	private static void write(List<String> argCategoryList,
			BufferedWriter argBufferedWriter) {
		for (String category : argCategoryList) {
			try {
				argBufferedWriter.write(category);
				argBufferedWriter.write("\r\n");
			} catch (IOException e) {
				throw new RuntimeException(
						"Exception while writing the content: ", e);
			}
		}
	}

	private static String createFilePath(int argFileCount,
			String argDirectoryName) {
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append(argDirectoryName);
		filePathBuilder.append("./category-" + argFileCount);
		return filePathBuilder.toString();
	}
}
