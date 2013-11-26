package com.data.generator.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.data.generator.constants.SparqlConstants;

public class Directory {
	public static void main(String[] args) throws IOException {
		File file = new File("db");
		file.mkdir();
		String fileName = new StringBuilder().append(SparqlConstants.DOT)
				.append(SparqlConstants.FORWARD_SLASH).append(file.getName())
				.append(SparqlConstants.FORWARD_SLASH).append("test")
				.toString();
		System.out.println(fileName + ".txt");
		File file1 = new File(fileName + ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
		bw.write("Hello...");
		bw.flush();
		bw.close();
	}
}
