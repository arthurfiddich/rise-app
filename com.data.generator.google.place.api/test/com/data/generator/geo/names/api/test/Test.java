package com.data.generator.geo.names.api.test;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
	private Queue<String> queue = new LinkedList<String>();

	private Test(Queue<String> argQueue) {
		super();
		this.queue = argQueue;
	}

	public void execute(String argUserName) {
		System.out.println(argUserName);
		String userName = argUserName;
		if (userName.equals("Amar3")) {
			System.out.println("Found");
		}
		for (int i = 0; i < 2; i++) {
			userName = queue.poll();
			execute(userName);
		}
	}

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		queue.add("Amar");
		queue.add("Amar1");
		queue.add("Amar2");
		queue.add("Amar3");
		Test test = new Test(queue);
		test.execute(test.queue.peek());
	}
}
