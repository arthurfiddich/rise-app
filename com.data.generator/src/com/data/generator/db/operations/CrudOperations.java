package com.data.generator.db.operations;

public interface CrudOperations<T> {

	public void insert(T argName);

	public void update(T argName);
	
	public void upsert(T argName);
	
}
