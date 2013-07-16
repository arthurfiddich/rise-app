package com.rise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rise.common.model.Person;

@Service
public interface PersonService extends BaseService {

	public List<Person> getPersons();
}
