package com.fastcode.emailtest2.addons.emailbuilder.domain.emailvariable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailVariableEntity;

import com.querydsl.core.types.Predicate;

public interface IEmailVariableManager {

	 // CRUD Operations
    public EmailVariableEntity create(EmailVariableEntity email);

    public void delete(EmailVariableEntity email);

    public EmailVariableEntity update(EmailVariableEntity email);

    public EmailVariableEntity findById(Long emailId);
    
    public EmailVariableEntity findByName (String name);

    public Page<EmailVariableEntity> findAll(Predicate predicate,Pageable pageable);
	
	public List<EmailVariableEntity> findAll();

	public List<Long> findByNameIn(Set<String> allFieldsId);

	public List<EmailVariableEntity> findByPropertyType(String type);

	public List<EmailVariableEntity> findByPropertyTypeIn(List<String> types);
	
}

