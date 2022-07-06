package com.searchApp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.searchApp.model.CodeLookup;

public interface CodeRepository extends CrudRepository<CodeLookup,Integer>{
	
	public List<CodeLookup> findByCodeContaining(String code);
	
	public List<CodeLookup> findByDescriptionContaining(String description);
	
	public List<CodeLookup> findByCodeAndDescription(String code, String description);

}
