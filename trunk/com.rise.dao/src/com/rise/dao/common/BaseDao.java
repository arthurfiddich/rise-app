package com.rise.dao.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rise.common.model.Model;

@Repository
public interface BaseDao {

	public Class getPersistentClass();

	public void save(Model argModel);

	public void delete(Model argModel);

	public List<Model> findAll();

	public Model findById(Integer argId);

	public void deleteById(Integer argId);
}
