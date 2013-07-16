package com.rise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.EmploymentExperience;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.dao.common.EmploymentExperienceDao;
import com.rise.service.EmploymentExperienceService;

@Service
public class EmploymentExperienceServiceImpl extends BaseServiceImpl implements
		EmploymentExperienceService {

	@Autowired
	private EmploymentExperienceDao employmentExperienceDao;

	@Override
	public Class getPersistentClass() {
		return this.getBaseDao().getPersistentClass();
	}

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			EmploymentExperience employmentExperience = (EmploymentExperience) argModel;
			employmentExperience.setCreatedBy(1);
			employmentExperience.setDateCreated(new Date());
			employmentExperience.setModifiedBy(1);
			employmentExperience.setDateModified(new Date());
			Person person = this.getBaseDao().getPerson(
					employmentExperience.getPerson().getId());
			if (person != null) {
				employmentExperience.setPerson(person);
			}
			return this.getBaseDao().save(employmentExperience);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			EmploymentExperience employmentExperience = (EmploymentExperience) argModel;
			EmploymentExperience employmentExperienceFromDb = (EmploymentExperience) this
					.getBaseDao().findById(
							employmentExperience.getPerson().getId());
			employmentExperienceFromDb.setJobTitle(employmentExperience
					.getJobTitle());
			employmentExperienceFromDb.setFromDate(employmentExperience
					.getFromDate());
			employmentExperienceFromDb.setToDate(employmentExperience
					.getToDate());
			employmentExperienceFromDb.setDescription(employmentExperience
					.getDescription());

			this.getBaseDao().update(employmentExperienceFromDb);
		}
		return argModel;
	}

	@Override
	public EmploymentExperienceDao getBaseDao() {
		return this.employmentExperienceDao;
	}

}
