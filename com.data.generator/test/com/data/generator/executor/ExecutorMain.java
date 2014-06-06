package com.data.generator.executor;

public class ExecutorMain {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Executor executor = new Executor("db");
		//542355
		executor.setNumberOfRecords(100000);
		executor.setFileExtension("csv");
		executor.initialize();
		executor.generate();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
