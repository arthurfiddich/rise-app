package com.rise.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rise.common.model.Address;
import com.rise.common.util.constants.HibernateConstants;
import com.rise.service.BaseService;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	public BaseService baseService;

	@RequestMapping(value = HibernateConstants.CREATE, method = RequestMethod.GET)
	public String get(Model argModel) {
		argModel.addAttribute(HibernateConstants.ADDRESS, new Address());
		return HibernateConstants.VIEW_SLASH
				+ this.baseService.getPersistentClass().getSimpleName()
						.toLowerCase() + HibernateConstants.NEW;
	}

	@RequestMapping(value = HibernateConstants.LIST)
	public String listPersonNames(Model argModel) {
		List<com.rise.common.model.Model> addresses = baseService.findAll();
		argModel.addAttribute(HibernateConstants.ADDRESS, new Address());
		argModel.addAttribute(HibernateConstants.ADDRESSES, addresses);
		return HibernateConstants.VIEW_SLASH
				+ this.baseService.getPersistentClass().getSimpleName()
						.toLowerCase() + HibernateConstants.LIST;
	}

	@RequestMapping(value = HibernateConstants.EDIT, method = RequestMethod.GET)
	public String editAddress(Model argModel, Address argAddress) {
		Address address = (Address) this.baseService.findById(argAddress
				.getId());
		argModel.addAttribute(HibernateConstants.EDIT_ADDRESS, address);
		argModel.addAttribute(HibernateConstants.EDIT_MODE, true);
		return HibernateConstants.VIEW_SLASH
				+ this.baseService.getPersistentClass().getSimpleName()
						.toLowerCase() + HibernateConstants.EDIT;
	}

	@RequestMapping(value = HibernateConstants.DELETE
			+ HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String deleteAddress(@PathVariable("argId") int argId) {
		if (argId != -1) {
			this.baseService.deleteById(argId);
			return HibernateConstants.RE_DIRECT
					+ HibernateConstants.SLASH
					+ this.baseService.getPersistentClass().getSimpleName()
							.toLowerCase() + HibernateConstants.LIST;
		}
		return HibernateConstants.ERROR;
	}

	@RequestMapping(value = HibernateConstants.SAVE, method = RequestMethod.POST)
	public String save(Model argModel, @Validated Address argAddress) {
		Address address = (Address) this.baseService.save(argAddress);
		argModel.addAttribute(HibernateConstants.ADDRESS, address);
		return HibernateConstants.VIEW_SLASH
				+ this.baseService.getPersistentClass().getSimpleName()
						.toLowerCase() + HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.UPDATE, method = RequestMethod.POST)
	public String update(Model argModel, @Validated Address argAddress) {
		Address address = (Address) this.baseService.update(argAddress);
		argModel.addAttribute(HibernateConstants.ADDRESS, address);
		return HibernateConstants.VIEW_SLASH
				+ this.baseService.getPersistentClass().getSimpleName()
						.toLowerCase() + HibernateConstants.VIEW;
	}

	@RequestMapping(value = HibernateConstants.PATH_VARIABLE_ID, method = RequestMethod.GET)
	public String getPerson(@PathVariable String argId, Model argModel) {
		if (argId != null && !argId.isEmpty() && argModel != null) {
			Address address = (Address) this.baseService.findById(Integer
					.parseInt(argId));
			argModel.addAttribute(HibernateConstants.ADDRESS, address);
			return HibernateConstants.VIEW_SLASH
					+ this.baseService.getPersistentClass().getSimpleName()
							.toLowerCase() + HibernateConstants.VIEW;
		}
		return HibernateConstants.ERROR;
	}
}
