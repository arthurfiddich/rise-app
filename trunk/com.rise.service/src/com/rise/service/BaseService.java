package com.rise.service;

import java.util.List;

import com.rise.common.model.Model;

public interface BaseService {

	public Model save(Model argModel);

	public void delete(Model argModel);

	public List<Model> findAll();

	public Model findById(Integer argId);

	public void deleteById(Integer argId);
	
	public Model update(Model argModel);
}
