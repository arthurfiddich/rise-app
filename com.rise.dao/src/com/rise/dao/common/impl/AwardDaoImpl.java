package com.rise.dao.common.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rise.common.model.Award;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.common.util.checker.Checker;
import com.rise.common.util.checker.Precondition;
import com.rise.dao.common.AwardDao;

@Repository
public class AwardDaoImpl extends BaseDaoImpl implements AwardDao {

	private static final String PERSON_ID = "paramPersonId";
	private static final String GET_PERSON = "getPersonByAward";
	
	@Override
	public Class getPersistentClass() {
		return Award.class;
	}
	
	@Override
	public void deleteById(Integer argId) {
		Model model = findById(argId);
		super.delete(model);
	}
	
	@SuppressWarnings("unchecked")
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
