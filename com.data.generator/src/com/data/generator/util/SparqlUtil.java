package com.data.generator.util;

import java.io.OutputStream;

import com.data.generator.constants.SparqlConstants;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlUtil {

	public static ResultSet generate(String argEndPoint, String argQuery) {
		Precondition.checkNotEmpty(argQuery);
		String endPoint = argEndPoint;
		if (Precondition.checkEmpty(endPoint)) {
			endPoint = SparqlConstants.SPARQL_ENDPOINT;
		}
		QueryExecution queryExecution = null;
		Query query = QueryFactory.create(argQuery);
		queryExecution = QueryExecutionFactory.sparqlService(endPoint, query);
		return queryExecution.execSelect();
	}

	public static ResultSet generate(String argQuery) {
		return generate(SparqlConstants.SPARQL_ENDPOINT, argQuery);
	}

	public static void generateAndWrite(String argEndPoint, String argQuery,
			OutputStream argOutputStream) {
		Precondition.checkNotNull(argOutputStream);
		ResultSet resultSet = generate(argEndPoint, argQuery);
		ResultSetFormatter.out(argOutputStream, resultSet);
	}

	public static void generateAndWrite(String argQuery,
			OutputStream argOutputStream) {
		generateAndWrite(SparqlConstants.SPARQL_ENDPOINT, argQuery,
				argOutputStream);
	}

	public static boolean ask() {
		String spqrqlQuery = "ASK{}";
		Query query = QueryFactory.create(spqrqlQuery);
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(
				SparqlConstants.SPARQL_ENDPOINT, query);
		return queryExecution.execAsk();
	}
}
