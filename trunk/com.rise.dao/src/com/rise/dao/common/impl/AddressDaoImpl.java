package com.rise.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.rise.common.model.Address;
import com.rise.common.model.Model;
import com.rise.dao.common.AddressDao;

@Repository
public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {

	@Override
	public Class getPersistentClass() {
		return Address.class;
	}

	@Override
	public Model update(Model argModel) {
		if (argModel != null) {
			this.getCurrentSession().saveOrUpdate(
					this.getPersistentClass().getName(), argModel);
			return findById(argModel.getId());
		}
		return argModel;
	}
}
