package com.rise.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.util.constants.HibernateConstants;

@Controller
@RequestMapping("/setup")
public class SetupController {

	@RequestMapping(value = HibernateConstants.SLASH, method = RequestMethod.GET)
	public String home(Model argModel) {
		return HibernateConstants.SET_UP;
	}
}
