package com.rise.common.util.controller.components;

public class Field {

	private String fieldName;
	private boolean checked;

	public Field() {
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * @param argFieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String argFieldName) {
		this.fieldName = argFieldName;
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
