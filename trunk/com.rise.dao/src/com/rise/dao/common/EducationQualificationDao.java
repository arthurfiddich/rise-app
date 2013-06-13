package com.rise.dao.common;

import java.util.List;

import com.rise.common.model.Person;
import com.rise.common.model.State;

public interface EducationQualificationDao extends BaseDao {

	public Person getPerson(int argId);
	
	public  List<State> getStates();
}
