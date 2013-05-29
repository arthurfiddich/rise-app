package com.rise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rise.common.model.Address;
import com.rise.common.model.Model;
import com.rise.dao.common.AddressDao;
import com.rise.dao.common.BaseDao;
import com.rise.service.AddressService;

@Service
public class AddressServiceImpl extends BaseServiceImpl implements
		AddressService {
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public Class getPersistentClass() {
		return Address.class;
	}
	
	@Transactional
	public Model save(Model argModel) {
		if (argModel != null) {
			Address address = (Address) argModel;
			address.setDateCreated(new Date());
			address.setModifiedBy(1);
			address.setCreatedBy(1);
			address.setDateModified(new Date());
			this.getBaseDao().save(argModel);
		}
		return argModel;
	}
	
	@Transactional
	public Model update(Model argModel) {
		if (argModel != null) {
			Address address = (Address) argModel;
			address.setDateCreated(new Date());
			address.setModifiedBy(1);
			address.setCreatedBy(1);
			address.setDateModified(new Date());
			return this.getBaseDao().update(address);
		}
		return argModel;
	}

	@Override
	public BaseDao getBaseDao() {
		return this.addressDao;
	}

}
