package com.rise.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rise.service.BaseService;
import com.rise.service.EducationQualificationService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Autowired
	private EducationQualificationService educationQualificationService;

	@RequestMapping("/")
	public String login() {
		return "/login";
	}

	public List<String> getStates() {
		List<String> states = this.getBaseService().getStates();
		return states;
	}

	@Override
	public EducationQualificationService getBaseService() {
		return this.educationQualificationService;
	}
}
