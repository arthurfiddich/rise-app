package com.rise.service.impl;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.BaseModel;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.common.model.PersonName;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.hibernate.QueryBuilder;
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
			person.getContactInformation().getPrimaryAddress()
					.setDateCreated(new Date());
			person.getContactInformation().getPrimaryAddress().setModifiedBy(1);
			person.getContactInformation().getPrimaryAddress()
					.setDateModified(new Date());

			return this.getBaseDao().save(argModel);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Person person = (Person) argModel;
			Person personFromDb = (Person) this.getBaseDao().findById(
					person.getId());
			personFromDb.getPersonName().setTitle(
					person.getPersonName().getTitle());
			personFromDb.getPersonName().setFirstName(
					person.getPersonName().getFirstName());
			personFromDb.getPersonName().setMiddleName(
					person.getPersonName().getMiddleName());
			personFromDb.getPersonName().setLastName(
					person.getPersonName().getLastName());
			personFromDb.getPersonName().setSuffix(
					person.getPersonName().getSuffix());
			personFromDb.setAadhaarNumber(person.getAadhaarNumber());
			personFromDb.setDateOfBirth(person.getDateOfBirth());

			personFromDb.getContactInformation().setEmail1(
					person.getContactInformation().getEmail1());
			personFromDb.getContactInformation().setEmail2(
					person.getContactInformation().getEmail2());
			personFromDb.getContactInformation().setEmail3(
					person.getContactInformation().getEmail3());
			personFromDb.getContactInformation().setPhone1(
					person.getContactInformation().getPhone1());
			personFromDb.getContactInformation().setPhone2(
					person.getContactInformation().getPhone2());
			personFromDb.getContactInformation().setPhone3(
					person.getContactInformation().getPhone3());
			personFromDb.getContactInformation().setMobile1(
					person.getContactInformation().getMobile1());
			personFromDb.getContactInformation().setMobile2(
					person.getContactInformation().getMobile2());
			personFromDb.getContactInformation().setMobile3(
					person.getContactInformation().getMobile3());
			personFromDb.getContactInformation().setWebsite(
					person.getContactInformation().getWebsite());

			personFromDb
					.getContactInformation()
					.getPrimaryAddress()
					.setStreetAddress(
							person.getContactInformation().getPrimaryAddress()
									.getStreetAddress());
			personFromDb
					.getContactInformation()
					.getPrimaryAddress()
					.setCity(
							person.getContactInformation().getPrimaryAddress()
									.getCity());
			personFromDb
					.getContactInformation()
					.getPrimaryAddress()
					.setState(
							person.getContactInformation().getPrimaryAddress()
									.getState());
			personFromDb
					.getContactInformation()
					.getPrimaryAddress()
					.setPostalCode(
							person.getContactInformation().getPrimaryAddress()
									.getPostalCode());
			personFromDb
					.getContactInformation()
					.getPrimaryAddress()
					.setCountry(
							person.getContactInformation().getPrimaryAddress()
									.getCountry());

			return this.getBaseDao().update(personFromDb);
		}
		return argModel;
	}

	@Override
	public PersonDao getBaseDao() {
		return this.personDao;
	}

	@Transactional
	public List<Person> getPersons() {
		List<Class> allClassesList = new ArrayList<Class>();
		List<Class> componentClassList = new ArrayList<Class>();
		List<Class> superClassList = new ArrayList<Class>();
		componentClassList.add(PersonName.class);
		superClassList.add(BaseModel.class);
		// allClassesList.add(this.getPersistentClass());
		allClassesList.addAll(componentClassList);
		// allClassesList.addAll(superClassList);
		String query = TenantConfigHelper.getInstance().buildQuery(
				this.getPersistentClass(), componentClassList, superClassList);
		query = QueryBuilder.buildQuery(this.getPersistentClass()
				.getSimpleName(), query);
		List<Object[]> results = this.getBaseDao().getPersons(query);
		try {
			List<Object> objects = assignProperties(results, allClassesList, this.getPersistentClass());
			List<Person> personsList = new ArrayList<Person>();
			for (Object object : objects) {
				personsList.add((Person) object);
			}
			return personsList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}

	protected List<Object> assignProperties(List<Object[]> argResults,
			List<Class> argAllClassesList, Class argParentClass)
			throws ClassNotFoundException, NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		List<Object[]> results = (List<Object[]>) Precondition.ensureNotEmpty(
				argResults, "Output List");
		List<Object> personClassList = new ArrayList<Object>();
		for (Object[] recordValues : results) {
			Class parentClass = Class.forName(this.getPersistentClass()
					.getName());
			String paramName = Introspector.decapitalize(parentClass
					.getSimpleName());
			Object parentClassInstance = parentClass.newInstance();
			int count = 0;
			count = assignValues(recordValues, parentClassInstance, count,
					paramName);
			for (Class clazz : argAllClassesList) {
				Class componentClass = Class.forName(clazz.getName());
				Object componentClassInstance = componentClass.newInstance();
				String decapitalize = Introspector.decapitalize(clazz
						.getSimpleName());
				Field field = parentClass.getDeclaredField(decapitalize);
				field.setAccessible(true);
				assignValues(recordValues, componentClassInstance, count,
						decapitalize);
				// BeanUtils
				// .setProperty(parentClass, decapitalize, componentClass);
				field.set(parentClassInstance, componentClassInstance);
			}
			personClassList.add(parentClassInstance);
		}
		return personClassList;
	}

	private int assignValues(Object[] argRecordValues,
			Object argParentClassInstance, int argCount, String argParamName) {
		if (Precondition.checkNotEmpty(argParamName)) {
			List<Field> fieldNames = TenantConfigHelper.getInstance()
					.getModelNameVsFieldsMap().get(argParamName);
			try {
				for (Field field : fieldNames) {
					BeanUtils.setProperty(argParentClassInstance,
							field.getName(), argRecordValues[argCount]);
					++argCount;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return argCount;
	}

	protected void assignProperties(List<Object[]> argResults,
			List<Class> argAllClassesList) throws ClassNotFoundException {
		List<Object[]> results = (List<Object[]>) Precondition.ensureNotEmpty(
				argResults, "Output List");
		int count = 0;
		for (int i = 0; i < results.size(); i++) {
			Class parentClass = Class.forName(this.getPersistentClass()
					.getName());
			Object[] outputValues = results.get(i);
			for (Class<?> clazz : argAllClassesList) {
				for (int j = count; j < outputValues.length; j++) {
					String paramName = Introspector.decapitalize(clazz
							.getSimpleName());
					if (Precondition.checkNotEmpty(paramName)) {
						List<Field> fieldNames = TenantConfigHelper
								.getInstance().getModelNameVsFieldsMap()
								.get(paramName);
						if (Precondition.checkNotEmpty(fieldNames)) {
							Class componentClass = null;
							if (clazz.getSimpleName().contains("personName")) {
								componentClass = Class.forName(clazz.getName());
							}
							for (Field field : fieldNames) {
								if (fieldNames.size() != j) {
									try {
										if (Precondition
												.checkNotNull(componentClass)) {
											BeanUtils.setProperty(
													componentClass,
													field.getName(),
													outputValues[count]);
										} else {
											BeanUtils.setProperty(parentClass,
													field.getName(),
													outputValues[count]);
										}
										count++;
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									}
								} else {
									j = outputValues.length;
									count += fieldNames.size();
								}
							}
						}
					}
				}
			}
		}
	}
}
