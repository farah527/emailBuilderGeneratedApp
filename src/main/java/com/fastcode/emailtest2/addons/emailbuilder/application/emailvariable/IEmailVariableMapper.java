package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import org.mapstruct.Mapper;
import java.util.List;

import com.fastcode.emailtest2.addons.emailbuilder.application.datasource.dto.DataSourceMetaOutput;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.DataSourceMetaEntity;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.dto.*;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailVariableEntity;

@Mapper(componentModel = "spring")
public interface IEmailVariableMapper {

    EmailVariableEntity createEmailVariableInputToEmailVariableEntity(CreateEmailVariableInput emailDto);

    CreateEmailVariableOutput emailVariableEntityToCreateEmailVariableOutput(EmailVariableEntity entity);

    EmailVariableEntity updateEmailVariableInputToEmailVariableEntity(UpdateEmailVariableInput emailDto);

    UpdateEmailVariableOutput emailVariableEntityToUpdateEmailVariableOutput(EmailVariableEntity entity);

    FindEmailVariableByIdOutput emailVariableEntityToFindEmailVariableByIdOutput(EmailVariableEntity entity);

    FindEmailVariableByNameOutput emailVariableEntityToFindEmailVariableByNameOutput(EmailVariableEntity entity);
    
    List<DataSourceMetaOutput> dataSourceEntityToDataSourceMetaList(List<DataSourceMetaEntity> list);
    
}
