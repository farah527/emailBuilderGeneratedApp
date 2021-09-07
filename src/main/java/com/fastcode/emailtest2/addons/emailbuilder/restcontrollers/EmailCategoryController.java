package com.fastcode.emailtest2.addons.emailbuilder.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.EmailCategoryService;

@RestController
public class EmailCategoryController {

	@Autowired
	private EmailCategoryService emailCategoryService;

	@GetMapping("/email/categories")
	ResponseEntity<String> getAll() throws Exception {
		return new ResponseEntity(emailCategoryService.getAllCategories(), HttpStatus.OK);
	}

}
