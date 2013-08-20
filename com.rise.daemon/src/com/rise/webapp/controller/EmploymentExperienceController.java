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

import com.rise.common.model.EmploymentExperience;
import com.rise.common.model.Person;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.service.BaseService;
import com.rise.service.EmploymentExperienceService;

@Controller
@RequestMapping("/employmentexperience")
public class EmploymentExperienceController extends BaseController {

	@Autowired
	private EmploymentExperienceService employmentExperienceService;

	private Logger logger = LoggerFactory
			.getLogger(EmploymentExperienceController.class);

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String get(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController Create Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new EmploymentExperience());
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.NEW;
	}

	@RequestMapping(value = HibernateConstants.CREATE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String create(@PathVariable String argId, Model argModel) {
		if (argId != null && !argId.isEmpty() && argModel != null) {
			EmploymentExperience employmentExperience = new EmploymentExperience();
			Person person = new Person();
			person.setId(Integer.parseInt(argId));
			employmentExperience.setPerson(person);
			argModel.addAttribute(getSimpleName(), employmentExperience);
			return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
					+ HibernateConstants.NEW;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.VIEW, method = RequestMethod.GET)
	public String view(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController EmploymentExperienceView Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new EmploymentExperience());
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.LIST)
	public String listEmploymentExperiences(Model argModel) {
		logger.error("Hello....................");
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController List Method: #################################");
		}
		List<com.rise.common.model.Model> employmentExperiences = this.getBaseService()
				.findAll();
		argModel.addAttribute(getSimpleName(), new EmploymentExperience());
		argModel.addAttribute(getFullyQualifiedName(), employmentExperiences);
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.LIST;
	}

	@RequestMapping(value = HibernateConstants.EDIT
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String editEducationQualification(Model argModel,
			@PathVariable("argId") int argId) {
		EmploymentExperience employmentExperience = (EmploymentExperience) this
				.getBaseService().findById(argId);
		argModel.addAttribute(HibernateConstants.EDIT_EMPLOYMENT_EXPERIENCE,
				employmentExperience);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.EDIT, method = RequestMethod.GET)
	public String editEmploymentExperience(Model argModel,
			EmploymentExperience argEmploymentExperience) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController Edit Method: #################################");
		}
		EmploymentExperience employmentExperience = (EmploymentExperience) this
				.getBaseService().findById(argEmploymentExperience.getId());
		argModel.addAttribute(HibernateConstants.EDIT_EMPLOYMENT_EXPERIENCE,
				employmentExperience);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.DELETE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String deleteEmploymentExperience(@PathVariable("argId") int argId) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController Delete Method: #################################");
		}
		if (argId != -1) {
			this.getBaseService().deleteById(argId);
			return HibernateConstants.RE_DIRECT + HibernateConstants.SLASH
					+ getClassNameInLowerCase() + HibernateConstants.LIST;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.SAVE, method = RequestMethod.POST)
	public String save(Model argModel,
			EmploymentExperience argEmploymentExperience) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController Save Method: #################################");
		}
		EmploymentExperience employmentExperience = (EmploymentExperience) this
				.getBaseService().save(argEmploymentExperience);
		argModel.addAttribute(getSimpleName(), employmentExperience);
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.UPDATE, method = RequestMethod.POST)
	public String update(Model argModel,
			@Validated EmploymentExperience argEmploymentExperience) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController Update Method: #################################");
		}
		EmploymentExperience employmentExperience = (EmploymentExperience) this
				.getBaseService().update(argEmploymentExperience);
		argModel.addAttribute(getSimpleName(), employmentExperience);
		return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String getEmploymentExperience(@PathVariable String argId, Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into EmploymentExperienceController FindByID Method: #################################");
		}
		if (argId != null && !argId.isEmpty() && argModel != null) {
			EmploymentExperience employmentExperience = (EmploymentExperience) this
					.getBaseService().findById(Integer.parseInt(argId));
			argModel.addAttribute(getSimpleName(), employmentExperience);
			return HibernateConstants.VIEW_SLASH + getClassNameInLowerCase()
					+ HibernateConstants.VIEW;
		}
		return HibernateConstants.ERROR;
	}

	@Override
	public BaseService getBaseService() {
		return this.employmentExperienceService;
	}

}
