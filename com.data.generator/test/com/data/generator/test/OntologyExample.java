package com.data.generator.test;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OntologyExample {
	public static void main(String[] args) {
		OntModel ontModel = ModelFactory.createOntologyModel();
		FileManager.get().readModel(ontModel, "./resources/dbpedia_3.9.owl");
		// ontModel.read("./resources/dbpedia_3.9.owl");
		ExtendedIterator<OntClass> classes = ontModel.listClasses();
		while (classes.hasNext()) {
			OntClass ontClass = classes.next();
			System.out.print(ontClass.getURI());
			System.out.println("\t" + ontClass.getSuperClass().getURI());
		}
	}
}
