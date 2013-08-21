package com.rise.common.util.controller.components;

public class Entity {

	private String entityName;
	private boolean checked;

	public Entity() {
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return this.entityName;
	}

	/**
	 * @param argEntityName
	 *            the entityName to set
	 */
	public void setEntityName(String argEntityName) {
		this.entityName = argEntityName;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return this.checked;
	}

	/**
	 * @param argChecked
	 *            the checked to set
	 */
	public void setChecked(boolean argChecked) {
		this.checked = argChecked;
	}

}
