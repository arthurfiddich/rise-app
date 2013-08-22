package com.rise.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rise.common.model.Address;
import com.rise.common.model.Person;
import com.rise.common.model.State;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.service.AddressService;
import com.rise.service.BaseService;
import com.rise.service.EducationQualificationService;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private EducationQualificationService educationQualificationService;

	@RequestMapping(value = HibernateConstants.LOGIN, method = RequestMethod.GET)
	public String login(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.LOGIN;
	}

	@RequestMapping(value = HibernateConstants.HOME, method = RequestMethod.GET)
	public String home(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.HOME;
	}
	@RequestMapping(value = HibernateConstants.SET_UP, method = RequestMethod.GET)
	public String setUp(Model argModel) {
		//argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.SET_UP;
	}
	@RequestMapping(value = HibernateConstants.CANDIDATE, method = RequestMethod.GET)
	public String candidate(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Person());
		return HibernateConstants.CANDIDATE;
	}

	@RequestMapping(value = HibernateConstants.TRAINER, method = RequestMethod.GET)
	public String trainer(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.TRAINER;
	}

	@RequestMapping(value = HibernateConstants.AGENTS, method = RequestMethod.GET)
	public String agents(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.AGENTS;
	}

	@RequestMapping(value = HibernateConstants.OVERVIEW, method = RequestMethod.GET)
	public String overview(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.OVERVIEW;
	}

	@RequestMapping(value = HibernateConstants.CONTACTUS, method = RequestMethod.GET)
	public String contactus(Model argModel) {
		argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.CONTACTUS;
	}

	@Override
	public BaseService getBaseService() {
		// TODO Auto-generated method stub
		return this.addressService;
	}

	@RequestMapping(value = "/states", method = RequestMethod.POST)
	public @ResponseBody
	List<State> getStates() {
		List<State> states = this.educationQualificationService.getStates();
		return states;
	}
}
