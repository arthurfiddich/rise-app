package com.rise.webapp.controller;

import java.beans.Introspector;

import org.springframework.stereotype.Controller;

import com.rise.service.BaseService;

@Controller
public abstract class BaseController {

	// @Autowired
	// protected BaseService baseService;

	protected String getSimpleName() {
		return Introspector.decapitalize(this.getBaseService().getSimpleName());
//		 return this.getBaseService().getSimpleName().toLowerCase();
	}

	protected String getClassNameInLowerCase() {
		 return this.getBaseService().getSimpleName().toLowerCase();
	}
	
	protected String getFullyQualifiedName() {
		return this.getBaseService().getFullyQualifiedName().toLowerCase();
	}

	public abstract BaseService getBaseService();
}
