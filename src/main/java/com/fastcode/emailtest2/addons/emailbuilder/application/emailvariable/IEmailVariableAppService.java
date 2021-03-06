package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastcode.emailtest2.commons.search.SearchCriteria;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.dto.*;

@Service
public interface IEmailVariableAppService {

	CreateEmailVariableOutput create(CreateEmailVariableInput email);

    void delete(@Positive(message ="EmailId should be a positive value")Long eid);

    UpdateEmailVariableOutput update(@Positive(message ="EmailId should be a positive value") Long eid,UpdateEmailVariableInput email);

    FindEmailVariableByIdOutput findById(@Positive(message ="EmailId should be a positive value")Long eid);

    FindEmailVariableByNameOutput findByName(String name);
    
    List<FindEmailVariableByIdOutput> find(SearchCriteria search,Pageable pageable) throws Exception;
	
}

