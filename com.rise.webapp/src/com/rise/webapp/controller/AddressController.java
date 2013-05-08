package com.rise.webapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rise.common.model.Address;
import com.rise.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	public AddressService addressService;

	@RequestMapping("/list")
	public String listPersonNames(Model argModel) {
		List<com.rise.common.model.Model> addresses = addressService.findAll();
		argModel.addAttribute("address", new Address());
		argModel.addAttribute("addresses", addresses);
		return "address";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute("address") Address argAddress,
			BindingResult argBindingResult) {
		argAddress.setDateCreated(new Date());
		argAddress.setDateModified(new Date());
		argAddress.setCreatedBy(1);
		argAddress.setModifiedBy(1);
		// argAddress.setRecordStatus("A");
		this.addressService.save(argAddress);
		return "redirect:/list";
	}

	@RequestMapping("/delete/{addressId}")
	public String deleteAddress(@PathVariable("addressId") Integer argAddressId) {
		this.addressService.deleteById(argAddressId);
		return "redirect:/list";
	}

	@RequestMapping("/findById/{addressId}")
	public String findById(@PathVariable("addressId") Integer argAddressId,
			Model argModel) {
		Address address = (Address) this.addressService.findById(argAddressId);
		argModel.addAttribute("addressView", address);
		return "view";
	}
}
