package com.data.generator.test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.data.generator.Generator;
import com.data.generator.constants.SparqlConstants;
import com.data.generator.impl.DbpediaDataGenerator;
import com.data.generator.util.SparqlUtil;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class DataGeneratorTest {

	public static void main(String[] args) throws IOException {

		System.out.println(SparqlUtil.ask());
		String query = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> PREFIX owl:<http://dbpedia.org/ontology/> select DISTINCT ?name ?description where { ?x a owl:Person; rdfs:label ?name; owl:abstract ?description. FILTER(LANG(?name) = \"en\") FILTER(LANG(?description) = \"en\") } LIMIT 1000";
		DbpediaDataGenerator dbpediaDataGenerator = new DbpediaDataGenerator(
				"db", query, SparqlConstants.SPARQL_ENDPOINT);
		dbpediaDataGenerator.setOutputFileName("test.csv");
		dbpediaDataGenerator.run();

		ResultSet resultSet = SparqlUtil.generate(query);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				new FileOutputStream("./output/result.txt"));
		ResultSetFormatter.out(bufferedOutputStream, resultSet);
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
//		while (resultSet.hasNext()) {
//			QuerySolution querySolution = resultSet.next();
//			Iterator<String> names = querySolution.varNames();
//			while (names.hasNext()) {
//				RDFNode rdfNode = querySolution.get(names.next());
//				System.out.println(rdfNode.asNode().getLiteral().getValue());
//			}
//		}
	}
}
