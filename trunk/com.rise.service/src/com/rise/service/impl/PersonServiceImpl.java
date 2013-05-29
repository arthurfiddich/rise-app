package com.rise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Address;
import com.rise.common.model.ContactInformation;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.dao.common.BaseDao;
import com.rise.dao.common.PersonDao;
import com.rise.service.PersonService;

@Service
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public Class getPersistentClass() {
		return Person.class;
	}

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			Person person = (Person) argModel;
			person.setId(1000);
			person.setDateCreated(new Date());
			person.setModifiedBy(1);
			person.setCreatedBy(1);
			person.setDateModified(new Date());
			ContactInformation contactInformation = this.getBaseDao()
					.getContactInformationById(1);
			if (contactInformation != null) {
				person.setContactInformation(contactInformation);
			}
			this.getBaseDao().save(argModel);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Person person = (Person) argModel;
			person.setDateCreated(new Date());
			person.setModifiedBy(1);
			person.setCreatedBy(1);
			person.setDateModified(new Date());
			return this.getBaseDao().update(person);
		}
		return argModel;
	}

	@Override
	public PersonDao getBaseDao() {
		return this.personDao;
	}

}
