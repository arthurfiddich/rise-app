package com.rise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Award;
import com.rise.common.model.Model;
import com.rise.common.model.Person;
import com.rise.dao.common.AwardDao;
import com.rise.dao.common.BaseDao;
import com.rise.service.AwardService;

@Service
public class AwardServiceImpl extends BaseServiceImpl implements AwardService {

	@Autowired
	private AwardDao awardDao;

	@Override
	public Class getPersistentClass() {
		return this.getBaseDao().getPersistentClass();
	}

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			Award award = (Award) argModel;
			award.setDateCreated(new Date());
			award.setModifiedBy(1);
			award.setCreatedBy(1);
			award.setDateModified(new Date());
			Person person = this.getBaseDao().getPerson(
					award.getPerson().getId());
			if (person != null) {
				award.setPerson(person);
			}
			return this.getBaseDao().save(award);
		}
		return argModel;
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Award award = (Award) argModel;
			Award awardFromDb = (Award) this.getBaseDao().findById(
					award.getId());
			awardFromDb.setName(award.getName());
			awardFromDb.setIssuedBy(award.getIssuedBy());
			awardFromDb.setDateIssued(award.getDateIssued());
			awardFromDb.setDescription(award.getDescription());

			this.getBaseDao().update(awardFromDb);
		}
		return argModel;
	}

	@Override
	public AwardDao getBaseDao() {
		return this.awardDao;
	}

}
