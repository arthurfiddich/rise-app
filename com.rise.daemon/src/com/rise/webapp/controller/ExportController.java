package com.rise.webapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.common.util.controller.components.Export;
import com.rise.service.BaseService;
import com.rise.service.PersonService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private PersonService personService;

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String exportPage(Model argModel) {
		logger.info("Entered into the export controller");
		Export export = new Export();
		export.setEntityNameVsFieldListMap(TenantConfigHelper.getInstance()
				.getEntityNameVsFieldPojoListMap());
		argModel.addAttribute("export", export);
		return "dataexport";
	}

	@RequestMapping(value = HibernateConstants.EXPORT_AJAX_LIST, method = RequestMethod.POST)
	public @ResponseBody
	String exportajaxListPersonNames(@RequestParam("entity") String entity,
			@RequestParam("fieldarray") String fieldarray, Model argModel) {
		logger.error("Hello....................");
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController List Method: #################################");
		}
		System.out.println("entity :" + entity);
		System.out.println("fieldarray :" + fieldarray);
		List<String> tokens = TenantConfigHelper.getInstance().getModelNameVsFieldNamesMap()
				.get(entity.toLowerCase());
		this.personService.exportData(entity, tokens);
		return "hello";
	}

	@Override
	public BaseService getBaseService() {
		return null;
	}

}
