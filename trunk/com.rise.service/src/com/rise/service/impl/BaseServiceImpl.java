package com.rise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Model;
import com.rise.dao.common.BaseDao;
import com.rise.service.BaseService;

@Service
public abstract class BaseServiceImpl implements BaseService {

	// @Autowired
	// private BaseDao baseDao;

	@Transactional
	public void delete(Model argModel) {
		this.getBaseDao().delete(argModel);
	}

	@Transactional
	public List<Model> findAll() {
		return this.getBaseDao().findAll();
	}

	@Transactional
	public Model findById(Integer argId) {
		return this.getBaseDao().findById(argId);
	}

	@Transactional
	public void deleteById(Integer argId) {
		this.getBaseDao().deleteById(argId);
	}

	@Override
	public String getSimpleName() {
		return this.getPersistentClass().getSimpleName();
	}

	@Override
	public String getFullyQualifiedName() {
		return this.getPersistentClass().getName();
	}

	public abstract BaseDao getBaseDao();
}
