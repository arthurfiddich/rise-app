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
import com.rise.service.BaseService;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	public BaseService baseService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(Model argModel) {
		argModel.addAttribute("address", new Address());
		return "/view/"
				+ this.baseService.getPersistentClass().getSimpleName().toLowerCase()
				+ "/new";
	}

	@RequestMapping(value = "/list")
	public String listPersonNames(Model argModel) {
		List<com.rise.common.model.Model> addresses = baseService.findAll();
		argModel.addAttribute("address", new Address());
		argModel.addAttribute("addresses", addresses);
		return "/view/"
				+ this.baseService.getPersistentClass().getSimpleName().toLowerCase()
				+ "/addressList";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editAddress(Model argModel, Address argAddress) {
		Address address = (Address) this.baseService.findById(argAddress
				.getId());
		argModel.addAttribute("editAddress", address);
		argModel.addAttribute("editMode", true);
		return "/view/"
				+ this.baseService.getPersistentClass().getSimpleName().toLowerCase()
				+ "/editAddress";
	}

	@RequestMapping(value = "/delete/{addressId}", method = RequestMethod.GET)
	public String deleteAddress(@PathVariable("addressId") int argAddressId) {
		if (argAddressId != -1) {
			this.baseService.deleteById(argAddressId);
			return "redirect:/address/list";
		}
		return "error";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model argModel, @Validated Address argAddress) {
		Address address = (Address) this.baseService.save(argAddress);
		argModel.addAttribute("address", address);
		return "/view/"
				+ this.baseService.getPersistentClass().getSimpleName().toLowerCase()
				+ "/addressView";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model argModel, @Validated Address argAddress) {
		Address address = (Address) this.baseService.update(argAddress);
		argModel.addAttribute("address", address);
		return "/view/"
				+ this.baseService.getPersistentClass().getSimpleName().toLowerCase()
				+ "/addressView";
	}

	@RequestMapping(value = "/{argAddressId}", method = RequestMethod.GET)
	public String getPerson(@PathVariable String argAddressId, Model argModel) {
		if (argAddressId != null && !argAddressId.isEmpty() && argModel != null) {
			Address address = (Address) this.baseService.findById(Integer
					.parseInt(argAddressId));
			argModel.addAttribute("address", address);
			return "/view/"
					+ this.baseService.getPersistentClass().getSimpleName()
							.toLowerCase() + "/addressView";
		}
		return "error";
	}
}
