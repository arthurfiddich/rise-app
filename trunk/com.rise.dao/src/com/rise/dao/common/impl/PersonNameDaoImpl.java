package com.rise.dao.common.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.PersonName;
import com.rise.dao.common.PersonNameDao;

@Transactional
@Repository
public class PersonNameDaoImpl extends BaseDaoImpl implements PersonNameDao {

	@Override
	public Class getPersistentClass() {
		return PersonName.class;
	}

}
