package com.rise.dao.common.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rise.common.model.ContactInformation;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.common.util.checker.Checker;
import com.rise.dao.common.PersonDao;

@Repository
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {

	private static final String PARAM_CONTACT_INFORMATION_ID = "paramContactInformationId";
	private static final String GET_CONTACT_INFORMATION = "getContactInformation";

	@Override
	public Class getPersistentClass() {
		return Person.class;
	}

	@Override
	public ContactInformation getContactInformationById(int argId) {
		if (Checker.checkIntValue(argId)) {
			Query query = this.getCurrentSession().getNamedQuery(
					GET_CONTACT_INFORMATION);
			query.setInteger(PARAM_CONTACT_INFORMATION_ID, argId);
			// query.setString(PARAM_CONTACT_INFORMATION_ID,
			// String.valueOf(1234567890));
			List<ContactInformation> contactInformationList = query.list();
			if (contactInformationList != null
					&& contactInformationList.size() > 0) {
				for (ContactInformation contactInformation : contactInformationList) {
					Integer id = contactInformation.getId();
					if (id != null && id.intValue() != -1) {
						if (id.equals(argId)) {
							return contactInformation;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Model findById(Integer argId) {
//		Person person = (Person) super.findById(argId);
//		System.out.println(person.getContactInformation());
//		return person;
		return super.findById(argId);
	}

}
