package com.rise.webapp.binding;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class BindingResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> resultsList;
	private List<String> fieldsList;

	public BindingResult() {
		super();
	}

	public List<T> getResultsList() {
		return this.resultsList;
	}

	public void setResultsList(List<T> argResultsList) {
		this.resultsList = argResultsList;
	}

	public List<String> getFieldsList() {
		return this.fieldsList;
	}

	public void setFieldsList(List<String> argFieldsList) {
		this.fieldsList = argFieldsList;
	}

}
