package com.rise.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rise.common.model.Person;

@Service
public interface PersonService extends BaseService {

	public List<Person> getPersons();

	public void writeStream(String argEntityName, String argSelectedFieldNames,
			OutputStream argOutputStream);
}
