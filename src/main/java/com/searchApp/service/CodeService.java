package com.searchApp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.searchApp.dao.CodeRepository;
import com.searchApp.model.CodeLookup;

@Service
@Transactional
public class CodeService {
	
	private final CodeRepository codeRepository;

	public CodeService(CodeRepository codeRepository) {
		super();
		this.codeRepository = codeRepository;
	}
	
	public List<CodeLookup> findAll(){
		List<CodeLookup> codeList = new ArrayList<>();
		for(CodeLookup code : codeRepository.findAll()){
			codeList.add(code);
		}
		return codeList;
	}
	
	public CodeLookup findCode(int id){
		return codeRepository.findOne(id);
	}
	
	public void save(CodeLookup codeLookup){
		codeRepository.save(codeLookup);
	}
	
	public void delete(int id){
		codeRepository.delete(id);
	}

}
