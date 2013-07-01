package com.rise.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.EducationQualification;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.common.model.State;
import com.rise.dao.common.EducationQualificationDao;
import com.rise.service.EducationQualificationService;

@Service
public class EducationQualificationServiceImpl extends BaseServiceImpl
		implements EducationQualificationService {

	@Autowired
	private EducationQualificationDao educationQualificationDao;

	@Override
	public Class getPersistentClass() {
		return this.educationQualificationDao.getPersistentClass();
	}

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			EducationQualification educationQualification = (EducationQualification) argModel;
			educationQualification.setCreatedBy(1);
			educationQualification.setDateCreated(new Date());
			educationQualification.setModifiedBy(1);
			educationQualification.setDateModified(new Date());
			Person person = this.getBaseDao().getPerson(
					educationQualification.getPerson().getId());
			if (person != null) {
				educationQualification.setPerson(person);
			}
			return this.getBaseDao().save(educationQualification);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			EducationQualification educationQualification = (EducationQualification) argModel;
			educationQualification.setCreatedBy(1);
			educationQualification.setDateCreated(new Date());
			educationQualification.setModifiedBy(1);
			educationQualification.setDateModified(new Date());
			Person person = this.getBaseDao().getPerson(1000);
			if (person != null) {
				educationQualification.setPerson(person);
			}
			return this.getBaseDao().update(educationQualification);
		}
		return argModel;
	}

	@Override
	public EducationQualificationDao getBaseDao() {
		return this.educationQualificationDao;
	}

	@Transactional
	public List<State> getStates() {
		return this.getBaseDao().getStates();
	}

}
