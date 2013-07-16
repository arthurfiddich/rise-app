package com.rise.common.util.Helper;

import java.util.List;

public class QueryInfo {

	private String selectClasue;
	private String fromClause;
	private List<String> fields;
	private String query;

	public QueryInfo() {
		super();
	}

	public String getSelectClasue() {
		return this.selectClasue;
	}

	public void setSelectClasue(String argSelectClasue) {
		this.selectClasue = argSelectClasue;
	}

	public String getFromClause() {
		return this.fromClause;
	}

	public void setFromClause(String argFromClause) {
		this.fromClause = argFromClause;
	}

	public List<String> getFields() {
		return this.fields;
	}

	public void setFields(List<String> argFields) {
		this.fields = argFields;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String argQuery) {
		this.query = argQuery;
	}

}
