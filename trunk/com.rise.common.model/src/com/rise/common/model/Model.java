package com.rise.common.model;

import java.util.Date;

public interface Model {
	public abstract Integer getId();

	public abstract void setId(Integer argId);

	public abstract Integer getCreatedBy();

	public abstract void setCreatedBy(Integer argUserId);

	public abstract Date getDateCreated();

	public abstract void setDateCreated(Date argDate);

	public abstract Integer getModifiedBy();

	public abstract void setModifiedBy(Integer argUserId);

	public abstract Date getDateModified();

	public abstract void setDateModified(Date argDate);
}