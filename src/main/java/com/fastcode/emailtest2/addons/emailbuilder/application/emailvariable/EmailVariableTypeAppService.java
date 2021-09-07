package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fastcode.emailtest2.addons.emailbuilder.domain.irepository.IEmailVariableTypeRepository;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailVariableTypeEntity;

@Service
@Validated
public class EmailVariableTypeAppService implements IEmailVariableTypeAppService {
	
	@Autowired
	IEmailVariableTypeRepository emailVariableTypeRepository;

	@Override
	public List<EmailVariableTypeEntity> getAllTypes() throws Exception {
		return emailVariableTypeRepository.findAll();
	}

}
