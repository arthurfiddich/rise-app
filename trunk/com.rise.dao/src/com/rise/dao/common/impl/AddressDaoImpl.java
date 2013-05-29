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

}
