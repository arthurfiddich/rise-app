package com.rise.dao.common;

import java.util.List;

import com.rise.common.model.Person;

public interface EducationQualificationDao extends BaseDao {

	public Person getPerson(int argId);
	
	public List<String> getStates();
}
