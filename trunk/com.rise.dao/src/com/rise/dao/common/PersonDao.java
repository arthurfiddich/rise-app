package com.rise.dao.common;

import com.rise.common.model.ContactInformation;

public interface PersonDao extends BaseDao {

	public ContactInformation getContactInformationById(int argId);
}
