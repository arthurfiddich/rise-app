package com.rise.dao.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rise.common.model.EducationQualification;
import com.rise.common.model.Person;
import com.rise.common.model.State;
import com.rise.common.util.checker.Checker;
import com.rise.dao.common.EducationQualificationDao;

@Repository
public class EducationQualificationDaoImpl extends BaseDaoImpl implements
		EducationQualificationDao {

	private static final String PERSON_ID = "paramPersonId";
	private static final String GET_PERSON = "getPerson";

	@Override
	public Class<EducationQualification> getPersistentClass() {
		return EducationQualification.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Person getPerson(int argId) {
		if (Checker.checkIntValue(argId)) {
			Query query = this.getCurrentSession().getNamedQuery(GET_PERSON);
			query.setInteger(PERSON_ID, argId);
			List<Person> list = query.list();
			if (CheckList(list)) {
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

	public static boolean CheckList(List<Person> argList) {
		if (argList != null && !argList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public  List<State> getStates() {
		Query query = this.getCurrentSession().createSQLQuery(
				"select * from state");
		List<Object[]> list = query.list();
		List<State> stateslist = new ArrayList<State>();
		for(Object[] obj:list){
			State state = new State();
			state.setId(Integer.parseInt(obj[0].toString()));
			state.setCode(obj[1].toString());
			state.setName(obj[2].toString());
			stateslist.add(state);
		}
		//List<String> list = query.list();
		return stateslist;
	}
}
