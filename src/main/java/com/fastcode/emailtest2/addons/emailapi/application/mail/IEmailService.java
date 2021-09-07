package com.fastcode.emailtest2.addons.emailapi.application.mail;

import java.util.List;
import java.util.Map;

import com.fastcode.emailtest2.addons.docmgmt.domain.file.FileEntity;
import com.fastcode.emailtest2.addons.emailapi.application.mail.dto.CreateEmailInput;

public interface IEmailService {

//	void sendMessage(CreateEmailInput email);
	void sendMessage(String to, String cc, String bcc, String subject, String htmlContent, List<FileEntity> inlineImages, List<FileEntity> attachments,Map<Long,byte[]> imageDataSourceMap);

}

