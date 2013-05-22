package com.rise.service.impl;

import org.springframework.stereotype.Service;

import com.rise.common.model.Address;
import com.rise.service.AddressService;

@Service
public class AddressServiceImpl extends BaseServiceImpl implements
		AddressService {

	@Override
	public Class getPersistentClass() {
		return Address.class;
	}

	@Override
	public String getSimpleName() {
		return this.getPersistentClass().getSimpleName();
	}

	@Override
	public String getFullyQualifiedName() {
		return this.getPersistentClass().getName();
	}
}
