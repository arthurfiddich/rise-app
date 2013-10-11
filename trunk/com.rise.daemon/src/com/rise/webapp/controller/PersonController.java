package com.rise.webapp.controller;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rise.common.model.Person;
import com.rise.common.model.PersonName;
import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.service.PersonService;
import com.rise.webapp.binding.BindingResult;
import com.rise.webapp.validators.PersonValidator;

@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {

	@Autowired
	private PersonValidator personValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// this.baseValidator.setClazz(this.getBaseService().getPersistentClass());
		binder.setValidator(personValidator);
	}

	@Autowired
	private PersonService personService;
	private static final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String get(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController Create Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new Person());
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.NEW;
	}

	@RequestMapping(value = HibernateConstants.PERSON_CREATE, method = RequestMethod.GET)
	public String create(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController PersonCreate Method: #################################");
		}
		List<String> activeTabsList = AnnotationProcessor
				.getActiveTabNamesIncludeHome();
		argModel.addAttribute(getSimpleName(), new Person());
		argModel.addAttribute("className", "person");
		argModel.addAttribute(HibernateHelperConstants.ACTIVE_TABS_LIST,
				activeTabsList);
		return "/view/person/personNew";
		// return HibernateConstants.VIEW_SLASH + getSimpleName()
		// + HibernateConstants.PERSON;
	}

	@RequestMapping(value = HibernateConstants.PERSON_VIEW, method = RequestMethod.GET)
	public String view(Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController PersonView Method: #################################");
		}
		argModel.addAttribute(getSimpleName(), new Person());
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.PERSON_VIEW;
	}

	@RequestMapping(value = HibernateConstants.LIST)
	public String listPersonNames(@Valid Model argModel) {
		logger.error("Hello....................");
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController List Method: #################################");
		}
		List<com.rise.common.model.Model> persons = this.getBaseService()
				.findAll();
		argModel.addAttribute(getSimpleName(), new Person());
		argModel.addAttribute(getFullyQualifiedName(), persons);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.LIST;
	}

	@RequestMapping(value = HibernateConstants.EDIT, method = RequestMethod.GET)
	public String editPerson(Model argModel, Person argPerson) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController Edit Method: #################################");
		}
		Person person = (Person) this.getBaseService().findById(
				argPerson.getId());
		argModel.addAttribute(HibernateConstants.EDIT_PERSON, person);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.DELETE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String deletePerson(@PathVariable("argId") int argId) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController Delete Method: #################################");
		}
		if (argId != -1) {
			this.getBaseService().deleteById(argId);
			return HibernateConstants.RE_DIRECT + HibernateConstants.SLASH
					+ getSimpleName() + HibernateConstants.LIST;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.SAVE, method = RequestMethod.POST)
	public String save(Model argModel, @Valid Person argPerson,
			org.springframework.validation.BindingResult argBindingResult) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController Save Method: #################################");
		}
		if (argBindingResult.hasErrors()) {
			return HibernateConstants.VIEW_SLASH + getSimpleName()
					+ HibernateConstants.PERSON;
		}
		Person person = (Person) this.getBaseService().save(argPerson);
		argModel.addAttribute(getSimpleName(), person);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.UPDATE, method = RequestMethod.POST)
	public String update(Model argModel, @Validated Person argPerson) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController Update Method: #################################");
		}
		Person person = (Person) this.getBaseService().update(argPerson);
		argModel.addAttribute(getSimpleName(), person);
		return HibernateConstants.VIEW_SLASH + getSimpleName()
				+ HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String getPerson(@PathVariable String argId, Model argModel) {
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController FindByID Method: #################################");
		}
		if (argId != null && !argId.isEmpty() && argModel != null) {
			Person person = (Person) this.getBaseService().findById(
					Integer.parseInt(argId));
			argModel.addAttribute(getSimpleName(), person);
			return HibernateConstants.VIEW_SLASH + getSimpleName()
					+ HibernateConstants.VIEW;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.AJAX_LIST)
	public @ResponseBody
	BindingResult<Person> ajaxListPersonNames(Model argModel) {
		logger.error("Hello....................");
		if (logger.isTraceEnabled()) {
			logger.trace("################################# Entered into PersonController List Method: #################################");
		}
		BindingResult<Person> bindingResult = new BindingResult<Person>();
		List<Person> persons = this.getBaseService().getPersons();
		if (Precondition.checkNotEmpty(persons)) {
			bindingResult.setResultsList(persons);
		}
		List<String> fieldsBasedOnClass = getAllFields();
		bindingResult.setFieldsList(fieldsBasedOnClass);
		return bindingResult;
	}

	private List<String> getAllFields() {
		String simpleName = Introspector.decapitalize(this.getBaseService()
				.getPersistentClass().getSimpleName());
		List<String> fieldsList = new ArrayList<String>();
		fieldsList.addAll(TenantConfigHelper.getInstance()
				.getFieldsBasedOnClass(simpleName));
		String componentClassName = Introspector.decapitalize(PersonName.class
				.getSimpleName());
		fieldsList.addAll(TenantConfigHelper.getInstance()
				.getFieldsBasedOnClass(componentClassName));
		Class clazz = this.getBaseService().getPersistentClass()
				.getSuperclass();
		String superClassName = Introspector
				.decapitalize(clazz.getSimpleName());
		fieldsList.addAll(TenantConfigHelper.getInstance()
				.getFieldsBasedOnClass(superClassName));
		return fieldsList;
	}

	@Override
	public PersonService getBaseService() {
		return this.personService;
	}

}
