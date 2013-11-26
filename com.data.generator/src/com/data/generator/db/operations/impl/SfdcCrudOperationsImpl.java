package com.data.generator.db.operations.impl;

import com.data.generator.db.operations.CrudOperations;

public class SfdcCrudOperationsImpl implements CrudOperations<String> {

	private final SfdcAuthentication sfdcAuthentication;

	public SfdcCrudOperationsImpl(SfdcAuthentication argSfdcAuthentication) {
		super();
		this.sfdcAuthentication = argSfdcAuthentication;
	}

	@Override
	public void insert(String argFileName) {

	}

	@Override
	public void update(String argFileName) {

	}

	@Override
	public void upsert(String argFileName) {

	}

	public SfdcAuthentication getSfdcAuthentication() {
		return this.sfdcAuthentication;
	}

}
