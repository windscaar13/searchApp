package com.searchApp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchApp.dao.CodeRepository;
import com.searchApp.dao.CodeSearchRepository;
import com.searchApp.model.CodeLookup;
import com.searchApp.service.CodeService;


@Controller
public class MainController {
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private CodeRepository codeRepository;
	
	@Autowired
	private CodeSearchRepository codeSearchRepository;
	
	@GetMapping("/")
	public String home(HttpServletRequest request){
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}

	@GetMapping("/all-codes")
	public String allCodes(HttpServletRequest request){
		request.setAttribute("codes", codeService.findAll());
		request.setAttribute("mode", "MODE_CODES");
		return "index";
	}
	
	@GetMapping("/new-code")
	public String newCode(HttpServletRequest request){
		request.setAttribute("mode", "MODE_NEW");
		return "index";
	}
	
	@PostMapping("/save-codes")
	public String saveTask(@ModelAttribute CodeLookup codeLookup, BindingResult bindingResult, HttpServletRequest request){
		codeService.save(codeLookup);
		request.setAttribute("codes", codeService.findAll());
		request.setAttribute("mode", "MODE_CODES");
		return "index";
	}
	
	@GetMapping("/update-code")
	public String updateTask(@RequestParam int id, HttpServletRequest request){
		request.setAttribute("code", codeService.findCode(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "index";
	}
	
	@GetMapping("/delete-code")
	public String deleteTask(@RequestParam int id, HttpServletRequest request){
		codeService.delete(id);
		request.setAttribute("codes", codeService.findAll());
		request.setAttribute("mode", "MODE_CODES");
		return "index";
	}
	
	@GetMapping("/search")
	public String search(HttpServletRequest request){
		request.setAttribute("mode", "MODE_SEARCH");
		return "index";
	}
	
	@PostMapping("/code-search")
	public String codeSearch(@ModelAttribute CodeLookup codeLookup, BindingResult bindingResult, HttpServletRequest request) {
		String code = codeLookup.getCode(); String description = codeLookup.getDescription();
		List<CodeLookup> codes = null;
		String mod = "%";
		if(!code.isEmpty() && description.isEmpty()) {
			code = mod+code+mod;
			codes = codeRepository.findByCodeContaining(code);
			request.setAttribute("codes", codes);
			request.setAttribute("mode", "MODE_SEARCH");
			request.setAttribute("result", "MODE_RESULT");
		}else if(code.isEmpty() && !description.isEmpty()) {
			description = mod+description+mod;
			codes = codeRepository.findByDescriptionContaining(description);
			request.setAttribute("codes", codes);
			request.setAttribute("mode", "MODE_SEARCH");
			request.setAttribute("result", "MODE_RESULT");
		}else if(!code.isEmpty() && !description.isEmpty()) {
			codes = codeSearchRepository.findByCodeAndDescription(code, description);
			request.setAttribute("codes", codes);
			request.setAttribute("mode", "MODE_SEARCH");
			request.setAttribute("result", "MODE_RESULT");
		}else if(code.isEmpty() && description.isEmpty()){
			request.setAttribute("mode", "MODE_SEARCH");
			request.setAttribute("result", "MODE_SEARCH_FAIL");
		}
		System.out.println(codes);
		return "index";
	}
	
	
}
