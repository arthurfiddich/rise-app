package com.rise.webapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rise.common.model.PersonName;
import com.rise.dao.common.PersonNameDao;

@Controller
public class PersonNameController {

	@Autowired
	private PersonNameDao personNameDao;

	@RequestMapping("/list")
	public String listPersonNames(Model argModel) {
		List<com.rise.common.model.Model> models = personNameDao.findAll();
		argModel.addAttribute("personName", new PersonName());
		argModel.addAttribute("personNames", models);
		return "personName";
	}

	@RequestMapping("/create")
	public String create(
			@ModelAttribute("personName") PersonName argPersonName,
			BindingResult argBindingResult) {
		argPersonName.setDateCreated(new Date());
		argPersonName.setDateModified(new Date());
		argPersonName.setCreatedBy(1);
		argPersonName.setModifiedBy(1);
		argPersonName.setRecordStatus("A");
		this.personNameDao.save(argPersonName);
		return "redirect:/list";
	}
}
