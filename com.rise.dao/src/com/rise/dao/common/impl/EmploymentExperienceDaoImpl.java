package com.rise.dao.common.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rise.common.model.EmploymentExperience;
import com.rise.common.model.Person;
import com.rise.common.util.checker.Checker;
import com.rise.common.util.checker.Precondition;
import com.rise.dao.common.EmploymentExperienceDao;

@Repository
public class EmploymentExperienceDaoImpl extends BaseDaoImpl implements
		EmploymentExperienceDao {
	
	private static final String PERSON_ID = "paramPersonId";
	private static final String GET_PERSON = "getPersonByEmploymentExperience";

	@Override
	public Class getPersistentClass() {
		return EmploymentExperience.class;
	}

	@Override
	public Person getPerson(int argId) {
		if (Checker.checkIntValue(argId)) {
			Query query = this.getCurrentSession().getNamedQuery(GET_PERSON);
			query.setInteger(PERSON_ID, argId);
			List<Person> list = query.list();
			if (Precondition.checkNotEmpty(list)) {
				for (Person Person : list) {
					Integer id = Person.getId();
					if (id != null && id.intValue() != -1) {
						if (id.equals(argId)) {
							return Person;
						}
					}
				}
			}
		}
		return null;
	}

}
