package com.rise.daemon.server.practice;

import org.eclipse.jetty.util.security.Password;

public class DeObfuscate {
	public static void main(String[] args) {
		String keyManagerPassword = "OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4";
		String keyStorePassword = "OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4";
		System.out.println("Key Manager Password: "
				+ Password.deobfuscate(keyManagerPassword));
		System.out.println("Key Store Password: "
				+ Password.deobfuscate(keyStorePassword));
	}
}
