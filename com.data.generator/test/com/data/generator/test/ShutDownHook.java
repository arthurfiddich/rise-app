package com.data.generator.test;

public class ShutDownHook {
	public static void main(String[] args) {
		JvmShutDownHook jvmShutDownHook = new JvmShutDownHook();
		Runtime.getRuntime().addShutdownHook(jvmShutDownHook);
		System.out.println("Jvm shutdown hook was registered...");
		String a = null;
		try {
			a.indexOf(1);
		} catch (Exception e) {
			throw new RuntimeException("Null pointer exception: ");
		}
		System.out.println("Post Exception...");
	}

	public static class JvmShutDownHook extends Thread {
		public void run() {
			System.out.println("Shutdown hook is calling...");
		}
	}
}
