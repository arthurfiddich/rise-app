package com.rise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.ContactInformation;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.dao.common.PersonDao;
import com.rise.service.PersonService;

@Service
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public Class<?> getPersistentClass() {
		return this.personDao.getPersistentClass();
	}

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			Person person = (Person) argModel;
			person.setDateCreated(new Date());
			person.setModifiedBy(1);
			person.setCreatedBy(1);
			person.setDateModified(new Date());
			
			person.getContactInformation().setCreatedBy(1);
			person.getContactInformation().setDateCreated(new Date());
			person.getContactInformation().setModifiedBy(1);
			person.getContactInformation().setDateModified(new Date());
			
			person.getContactInformation().getPrimaryAddress().setCreatedBy(1);
			person.getContactInformation().getPrimaryAddress().setDateCreated(new Date());
			person.getContactInformation().getPrimaryAddress().setModifiedBy(1);
			person.getContactInformation().getPrimaryAddress().setDateModified(new Date());
			
			return this.getBaseDao().save(argModel);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Person person = (Person) argModel;
			Person personFromDb = (Person) this.getBaseDao().findById(person.getId());
			personFromDb.getPersonName().setTitle(person.getPersonName().getTitle());
			personFromDb.getPersonName().setFirstName(person.getPersonName().getFirstName());
			personFromDb.getPersonName().setMiddleName(person.getPersonName().getMiddleName());
			personFromDb.getPersonName().setLastName(person.getPersonName().getLastName());
			personFromDb.getPersonName().setSuffix(person.getPersonName().getSuffix());
			personFromDb.setAadhaarNumber(person.getAadhaarNumber());
			personFromDb.setDateOfBirth(person.getDateOfBirth());
			
			personFromDb.getContactInformation().setEmail1(person.getContactInformation().getEmail1());
			personFromDb.getContactInformation().setEmail2(person.getContactInformation().getEmail2());
			personFromDb.getContactInformation().setEmail3(person.getContactInformation().getEmail3());
			personFromDb.getContactInformation().setPhone1(person.getContactInformation().getPhone1());
			personFromDb.getContactInformation().setPhone2(person.getContactInformation().getPhone2());
			personFromDb.getContactInformation().setPhone3(person.getContactInformation().getPhone3());
			personFromDb.getContactInformation().setMobile1(person.getContactInformation().getMobile1());
			personFromDb.getContactInformation().setMobile2(person.getContactInformation().getMobile2());
			personFromDb.getContactInformation().setMobile3(person.getContactInformation().getMobile3());
			personFromDb.getContactInformation().setWebsite(person.getContactInformation().getWebsite());
			
			personFromDb.getContactInformation().getPrimaryAddress().setStreetAddress(person.getContactInformation().getPrimaryAddress().getStreetAddress());
			personFromDb.getContactInformation().getPrimaryAddress().setCity(person.getContactInformation().getPrimaryAddress().getCity());
			personFromDb.getContactInformation().getPrimaryAddress().setState(person.getContactInformation().getPrimaryAddress().getState());
			personFromDb.getContactInformation().getPrimaryAddress().setPostalCode(person.getContactInformation().getPrimaryAddress().getPostalCode());
			personFromDb.getContactInformation().getPrimaryAddress().setCountry(person.getContactInformation().getPrimaryAddress().getCountry());
			
			return this.getBaseDao().update(personFromDb);
		}
		return argModel;
	}

	@Override
	public PersonDao getBaseDao() {
		return this.personDao;
	}

}
