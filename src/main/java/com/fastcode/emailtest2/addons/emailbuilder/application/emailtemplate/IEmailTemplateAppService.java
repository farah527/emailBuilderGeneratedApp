package com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastcode.emailtest2.commons.search.SearchCriteria;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto.*;
import com.fastcode.emailtest2.addons.emailbuilder.application.datasource.dto.FindDataSourceMetaOutputForMapping;

@Service
public interface IEmailTemplateAppService {

	CreateEmailTemplateOutput create(CreateEmailTemplateInput email);

    void delete(@Positive(message ="EmailId should be a positive value")Long eid);

    UpdateEmailTemplateOutput update(@Positive(message ="EmailId should be a positive value") Long eid,UpdateEmailTemplateInput email);

    FindEmailTemplateByIdOutput findById(@Positive(message ="EmailId should be a positive value")Long eid);

    FindEmailTemplateByNameOutput findByName(String name);
    
    List<FindEmailTemplateByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;

	List<FindDataSourceMetaOutputForMapping> getMappingForEmail(Long emailTemplateId) ;

    List<CreateEmailTemplateMappingInput> createMapping(List<CreateEmailTemplateMappingInput> mapping);

}

