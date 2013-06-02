package com.rise.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.rise.common.model.EducationQualification;
import com.rise.dao.common.EducationQualificationDao;

@Repository
public class EducationQualificationDaoImpl extends BaseDaoImpl implements
		EducationQualificationDao {

	@Override
	public Class getPersistentClass() {
		return EducationQualification.class;
	}

}
