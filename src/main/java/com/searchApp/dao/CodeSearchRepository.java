package com.searchApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.searchApp.model.CodeLookup;

public interface CodeSearchRepository extends JpaRepository<CodeLookup,Integer>{
	
	@Query("select c from code_lookup c where c.code like %?1% and c.description like %?2%")
	List<CodeLookup> findByCodeAndDescription(String code, String description);

}
