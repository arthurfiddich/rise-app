package com.rise.webapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.model.Award;
import com.rise.common.model.Person;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.service.AwardService;
import com.rise.service.BaseService;

@Controller
@RequestMapping("/award")
public class AwardController extends BaseController {

	@Autowired
	private AwardService awardService;

	private Logger logger = LoggerFactory.getLogger(AwardController.class);

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String get(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController Create Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new Award());
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.NEW;
	}

	@RequestMapping(value = HibernateConstants.CREATE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String create(@PathVariable String argId, Model argModel) {
		if (argId != null && !argId.isEmpty() && argModel != null) {
			Award award = new Award();
			Person person = new Person();
			person.setId(Integer.parseInt(argId));
			award.setPerson(person);
			argModel.addAttribute(getSimpleName(), award);
			return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
					+ HibernateConstants.NEW;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.VIEW, method = RequestMethod.GET)
	public String view(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController AwardView Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new Award());
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.LIST)
	public String listAwardNames(Model argModel) {
		logger.error("Hello....................");
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController List Method: #################################");
		}
		List<com.rise.common.model.Model> awards = this.getBaseService()
				.findAll();
		argModel.addAttribute(getSimpleName(), new Award());
		argModel.addAttribute(getFullyQualifiedName(), awards);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.LIST;
	}
	
	@RequestMapping(value = HibernateConstants.EDIT
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String editEducationQualification(Model argModel,
			@PathVariable("argId") int argId) {
		Award award = (Award) this.getBaseService().findById(argId);
		argModel.addAttribute(HibernateConstants.EDIT_AWARD, award);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.EDIT, method = RequestMethod.GET)
	public String editAward(Model argModel, Award argAward) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController Edit Method: #################################");
		}
		Award award = (Award) this.getBaseService().findById(argAward.getId());
		argModel.addAttribute(HibernateConstants.EDIT_AWARD, award);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.DELETE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String deleteAward(@PathVariable("argId") int argId) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController Delete Method: #################################");
		}
		if (argId != -1) {
			this.getBaseService().deleteById(argId);
			return HibernateConstants.RE_DIRECT + HibernateConstants.SLASH
					+ getSimpleName() + HibernateConstants.LIST;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.SAVE, method = RequestMethod.POST)
	public String save(Model argModel, Award argAward) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController Save Method: #################################");
		}
		Award award = (Award) this.getBaseService().save(argAward);
		argModel.addAttribute(getSimpleName(), award);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.UPDATE, method = RequestMethod.POST)
	public String update(Model argModel, @Validated Award argAward) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController Update Method: #################################");
		}
		Award award = (Award) this.getBaseService().update(argAward);
		argModel.addAttribute(getSimpleName(), award);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String getAward(@PathVariable String argId, Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into AwardController FindByID Method: #################################");
		}
		if (argId != null && !argId.isEmpty() && argModel != null) {
			Award award = (Award) this.getBaseService().findById(
					Integer.parseInt(argId));
			argModel.addAttribute(getSimpleName(), award);
			return HibernateConstants.VIEW_SLASH + getSimpleName()
					+ HibernateConstants.VIEW;
		}
		return HibernateConstants.ERROR;
	}

	@Override
	public BaseService getBaseService() {
		return this.awardService;
	}

}
