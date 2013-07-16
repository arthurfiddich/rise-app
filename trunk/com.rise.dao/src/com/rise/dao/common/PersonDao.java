package com.rise.dao.common;

import java.util.List;

import com.rise.common.model.ContactInformation;

public interface PersonDao extends BaseDao {

	public ContactInformation getContactInformationById(int argId);

	public List<Object[]> getPersons(String argQuery);
}
