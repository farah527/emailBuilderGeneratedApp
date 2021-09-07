package com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate;

import org.mapstruct.Mapper;

import com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto.*;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailTemplateEntity;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailtemplateEntityHistory;

@Mapper(componentModel = "spring")
public interface IEmailTemplateMapper {

    EmailTemplateEntity createEmailTemplateInputToEmailTemplateEntity(CreateEmailTemplateInput emailDto);

    CreateEmailTemplateOutput emailTemplateEntityToCreateEmailTemplateOutput(EmailTemplateEntity entity);

    EmailTemplateEntity updateEmailTemplateInputToEmailTemplateEntity(UpdateEmailTemplateInput emailDto);

    UpdateEmailTemplateOutput emailTemplateEntityToUpdateEmailTemplateOutput(EmailTemplateEntity entity);

    FindEmailTemplateByIdOutput emailTemplateEntityToFindEmailTemplateByIdOutput(EmailTemplateEntity entity);

    FindEmailTemplateByNameOutput emailTemplateEntityToFindEmailTemplateByNameOutput(EmailTemplateEntity entity);

	FindEmailTemplateByIdOutput emailTemplateEntityToFindEmailTemplateByIdOutputforReset(
			EmailtemplateEntityHistory foundEmail);

	EmailtemplateEntityHistory createEmailTemplateInputToEmailTemplateEntityforReset(CreateEmailTemplateInput email);

} 
