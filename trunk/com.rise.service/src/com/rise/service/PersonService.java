package com.rise.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rise.common.model.Person;
import com.rise.common.util.exception.DatabaseException;

@Service
public interface PersonService extends BaseService {

	public List<Person> getPersons();

	public void writeStream(String argEntityName, String argSelectedFieldNames,
			OutputStream argOutputStream);
	
	void exportData(String argTableName, List<String> argColumnNamesList)
			throws DatabaseException;
}
