package com.rise.webapp.initializor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.database.DatabaseUtil;

@SuppressWarnings("serial")
public class StartupHook extends HttpServlet {

	@Override
	public void init(ServletConfig argConfig) throws ServletException {
		GenerateFile.prepareClassesListFile("com.rise.common.model",
				"classNames.txt");
		TenantConfigHelper.getInstance();
		DatabaseUtil.getInstance();
		GenerateFile.prepareUiComponentsFile("com.rise.common.model",
				"uiComponent.txt");
		super.init(argConfig);
	}
}
