package com.fastcode.emailtest2.addons.emailbuilder.restcontrollers;

import java.io.IOException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.EmailTemplateAppService;
import com.fastcode.emailtest2.commons.domain.EmptyJsonResponse;

@RestController
@RequestMapping("/htmlEmail")
public class HtmlEmailController {

	 @Autowired
	 private EmailTemplateAppService emailTemplateAppService;

	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity<String> jsonToHtml(@RequestBody @Valid String json) throws IOException
	 {
		 if(json != null)
			 return new ResponseEntity(emailTemplateAppService.convertJsonToHtml(json), HttpStatus.OK);
		 else
			 return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
			 
	 }
}

