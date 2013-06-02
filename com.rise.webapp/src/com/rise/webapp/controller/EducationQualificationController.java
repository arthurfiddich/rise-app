package com.rise.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.model.EducationQualification;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.service.BaseService;
import com.rise.service.EducationQualificationService;

@Controller
@RequestMapping("/eq")
public class EducationQualificationController extends BaseController {

	@Autowired
	private EducationQualificationService educationQualificationService;

	@Override
	public BaseService getBaseService() {
		return this.educationQualificationService;
	}

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String get(Model argModel) {
		argModel.addAttribute(getSimpleName(), new EducationQualification());
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.NEW;
	}

	@RequestMapping(value = HibernateConstants.LIST)
	public String listEducationQualifications(Model argModel) {
		List<com.rise.common.model.Model> educationQualifications = this
				.getBaseService().findAll();
		argModel.addAttribute(getSimpleName(), new EducationQualification());
		argModel.addAttribute(getFullyQualifiedName(), educationQualifications);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.LIST;
	}

	@RequestMapping(value = HibernateConstants.EDIT, method = RequestMethod.GET)
	public String editEducationQualification(Model argModel,
			EducationQualification argEducationQualification) {
		EducationQualification educationQualification = (EducationQualification) this
				.getBaseService().findById(argEducationQualification.getId());
		argModel.addAttribute(HibernateConstants.EDIT_EDUCATION_QUALIFICATION,
				educationQualification);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.DELETE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String deleteEducationQualification(@PathVariable("argId") int argId) {
		if (argId != -1) {
			this.getBaseService().deleteById(argId);
			return HibernateConstants.RE_DIRECT + HibernateConstants.SLASH
					+ getSimpleName() + HibernateConstants.LIST;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.SAVE, method = RequestMethod.POST)
	public String save(Model argModel,
			@Validated EducationQualification argEducationQualification) {
		EducationQualification educationQualification = (EducationQualification) this
				.getBaseService().save(argEducationQualification);
		argModel.addAttribute(getSimpleName(), educationQualification);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.UPDATE, method = RequestMethod.POST)
	public String update(Model argModel,
			@Validated EducationQualification argEducationQualification) {
		EducationQualification educationQualification = (EducationQualification) this
				.getBaseService().update(argEducationQualification);
		argModel.addAttribute(getSimpleName(), educationQualification);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String getEducationQualification(@PathVariable String argId,
			Model argModel) {
		if (argId != null && !argId.isEmpty() && argModel != null) {
			EducationQualification educationQualification = (EducationQualification) this
					.getBaseService().findById(Integer.parseInt(argId));
			argModel.addAttribute(getSimpleName(), educationQualification);
			return HibernateConstants.VIEW_SLASH + getSimpleName()
					+ HibernateConstants.VIEW;
		}
		return HibernateConstants.ERROR;
	}

}
