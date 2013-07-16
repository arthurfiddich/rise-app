package com.rise.webapp.initializor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.rise.common.util.Helper.TenantConfigHelper;

public class StartupHook extends HttpServlet{

	@Override
	public void init(ServletConfig argConfig) throws ServletException {
		TenantConfigHelper.getInstance();
		super.init(argConfig);
	}
}
