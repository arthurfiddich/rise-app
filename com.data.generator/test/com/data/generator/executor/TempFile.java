package com.data.generator.executor;

import java.io.File;
import java.io.IOException;

public class TempFile {
	public static void main(String[] args) throws IOException {
		File file = File.createTempFile("test", "csv", new File("db"));
		System.out.println(file.getName());
	}
}
