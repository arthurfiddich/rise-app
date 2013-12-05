package com.data.generator.executor;

public class ForkJoinPoolMain {
	public static void main(String[] args) {
		ForkJoinExecutor forkJoinExecutor = new ForkJoinExecutor(64, "db");
		forkJoinExecutor.initialize();
		forkJoinExecutor.setNumberOfRecords(10000);
		forkJoinExecutor.setFileExtension("csv");
		forkJoinExecutor.getForkJoinPool().invoke(forkJoinExecutor);
	}
}
