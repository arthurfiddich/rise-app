package com.rise.dao.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rise.common.model.Model;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.dao.common.BaseDao;

@Repository
public abstract class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Model save(Model argModel) {
		if (argModel != null) {
			this.getCurrentSession().save(argModel);
//			Model model = findById(argModel.getId());
//			return model;
		}
		return argModel;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void delete(Model argModel) {
		if (argModel != null) {
			this.getCurrentSession().delete(argModel);
		}
	}

	@Override
	public List<Model> findAll() {
		String hqlQuery = HibernateConstants.SELECT_QUERY
				+ this.getPersistentClass().getName()
				+ HibernateConstants.ALIAS_NAME;
		Query query = this.getCurrentSession().createQuery(hqlQuery);
		List<Model> results = new ArrayList<Model>();
		if (query != null) {
			results = query.list();
		}
		return results;
	}

	@Override
	public Model findById(Integer argId) {
		if (argId != null && argId.intValue() != -1) {
			Model model = (Model) this.getCurrentSession().get(
					this.getPersistentClass(), argId);
			return model;
		}
		return null;
	}

	@Override
	public void deleteById(Integer argId) {
		if (argId != null && argId.intValue() != -1) {
			Model model = (Model) this.getCurrentSession().load(
					this.getPersistentClass(), argId);
			if (model != null) {
				this.getCurrentSession().delete(model);
			}
		}
	}
	
	@Override
	public Model update(Model argModel) {
		if (argModel != null) {
			this.getCurrentSession().update(
					this.getPersistentClass().getName(), argModel);
//			return findById(argModel.getId());
		}
		return argModel;
	}
}
