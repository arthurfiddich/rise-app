package com.data.generator.japan.university;

import java.util.List;

public class MemberInfo {
	private String[] headersArray;
	private List<List<String>> tokensList;
	private int emailCount;

	public MemberInfo() {
	}

	public String[] getHeadersArray() {
		return this.headersArray;
	}

	public void setHeadersArray(String[] argHeadersArray) {
		this.headersArray = argHeadersArray;
	}

	public List<List<String>> getTokensList() {
		return this.tokensList;
	}

	public void setTokensList(List<List<String>> argTokensList) {
		this.tokensList = argTokensList;
	}

	public int getEmailCount() {
		return this.emailCount;
	}

	public void setEmailCount(int argEmailCount) {
		this.emailCount = argEmailCount;
	}

}
