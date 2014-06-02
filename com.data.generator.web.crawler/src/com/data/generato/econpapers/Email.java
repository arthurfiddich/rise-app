package com.data.generato.econpapers;

public class Email {

	private String localPart;
	private char symbol;
	private String domainPart;

	public Email(String argLocalPart, char argSymbol, String argDomainPart) {
		super();
		this.localPart = argLocalPart;
		this.symbol = argSymbol;
		this.domainPart = argDomainPart;
	}

	public String getLocalPart() {
		return this.localPart;
	}

	public char getSymbol() {
		return this.symbol;
	}

	public String getDomainPart() {
		return this.domainPart;
	}

	@Override
	public String toString() {
		StringBuilder emailBuilder = new StringBuilder();
		emailBuilder.append(this.getLocalPart());
		emailBuilder.append(this.getSymbol());
		emailBuilder.append(this.getDomainPart());
		return emailBuilder.toString();
	}

}
