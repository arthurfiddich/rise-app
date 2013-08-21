package com.rise.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.common.util.controller.components.Export;
import com.rise.service.BaseService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(BaseController.class);

	@RequestMapping(value = HibernateConstants.SLASH, method = RequestMethod.GET)
	public String exportPage(Model argModel) {
		logger.info("Entered into the export controller");
		Export export = new Export();
		export.setEntityNameVsFieldListMap(TenantConfigHelper.getInstance()
				.getEntityNameVsFieldPojoListMap());
		argModel.addAttribute("export", export);
		return "home";
	}

	@Override
	public BaseService getBaseService() {
		return null;
	}

	// public void generateReport(HttpServletRequest request,
	// HttpServletResponse response, BindingResult argBindingResult,
	// String argJsonString) throws Exception {
	// System.out.println(argJsonString);
	// }
}
