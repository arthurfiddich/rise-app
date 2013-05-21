package com.rise.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Address;
import com.rise.common.model.Model;
import com.rise.dao.common.BaseDao;
import com.rise.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseDao baseDao;

	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			Address address = (Address) argModel;
			address.setDateCreated(new Date());
			address.setModifiedBy(1);
			address.setCreatedBy(1);
			address.setDateModified(new Date());
			this.baseDao.save(argModel);
		}
		return argModel;
	}

	@Transactional
	public void delete(Model argModel) {
		this.baseDao.delete(argModel);
	}

	@Transactional
	public List<Model> findAll() {
		return this.baseDao.findAll();
	}

	@Transactional
	public Model findById(Integer argId) {
		return this.baseDao.findById(argId);
	}

	@Transactional
	public void deleteById(Integer argId) {
		this.baseDao.deleteById(argId);
	}

	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Address address = (Address) argModel;
			address.setDateCreated(new Date());
			address.setModifiedBy(1);
			address.setCreatedBy(1);
			address.setDateModified(new Date());
			return this.baseDao.update(address);
		}
		return argModel;
	}

}
