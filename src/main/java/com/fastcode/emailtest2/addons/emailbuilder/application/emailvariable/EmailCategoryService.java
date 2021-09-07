package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fastcode.emailtest2.addons.emailbuilder.domain.irepository.IEmailTemplateRepository;

@Service
@Validated
public class EmailCategoryService implements IEmailCategoryService {
	
	@Autowired
	IEmailTemplateRepository emailTemplateRepository;

	@Override
	public List<String> getAllCategories() throws Exception {
		return emailTemplateRepository.findAllDistinctCategories();
	}

}
