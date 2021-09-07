package com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto;

import java.util.List;

import com.fastcode.emailtest2.addons.emailbuilder.application.datasource.dto.FindDataSourceMetaOutputForMapping;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.dto.FindEmailVariableByIdOutput;

public class FindEmailTemplateMappingDTO {

	private FindEmailVariableByIdOutput mergeField;

	private List<FindDataSourceMetaOutputForMapping> dataSourceMetaList;
	
}
