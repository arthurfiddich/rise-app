package com.rise.webapp.controller;

import java.beans.Introspector;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.service.BaseService;

@Controller
public abstract class BaseController {

	// @Autowired
	// protected BaseService baseService;

	private Logger logger = LogManager.getLogger(BaseController.class);
	private TenantConfigHelper tenantConfigHelper = TenantConfigHelper.getInstance();

	protected String getSimpleName() {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into BaseController class: #################################");
		}
		return Introspector.decapitalize(this.getBaseService().getSimpleName());
		// return this.getBaseService().getSimpleName().toLowerCase();
	}

	protected String getClassNameInLowerCase() {
		return this.getBaseService().getSimpleName().toLowerCase();
	}

	protected String getFullyQualifiedName() {
		return this.getBaseService().getFullyQualifiedName().toLowerCase();
	}

	public abstract BaseService getBaseService();
}
