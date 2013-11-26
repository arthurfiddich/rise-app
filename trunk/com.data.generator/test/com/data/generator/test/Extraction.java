package com.data.generator.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Extraction {
	public static void main(String[] args) throws IOException {
		String fileName = "D:\\Dell XPS\\DBPEDIA\\mappingbased_properties_en.nt\\mappingbased_properties_en.nt";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = null;
		int i = 0;
		while ((line = br.readLine()) != null) {
			if (i <= 100) {
				System.out.println(line);
			}
			++i;
		}
	}
}
