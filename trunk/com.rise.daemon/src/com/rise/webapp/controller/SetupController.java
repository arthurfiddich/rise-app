package com.rise.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.model.Address;
import com.rise.common.util.constants.HibernateConstants;

@Controller
@RequestMapping("/setUp")
public class SetupController {

	@RequestMapping(value = HibernateConstants.SET_UP, method = RequestMethod.GET)
	public String setUp(Model argModel) {
		//argModel.addAttribute(getSimpleName(), new Address());
		return HibernateConstants.SET_UP;
	}
}
