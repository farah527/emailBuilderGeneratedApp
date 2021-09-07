package com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fastcode.emailtest2.addons.docmgmt.domain.file.FileEntity;

@Getter @Setter
public class UpdateEmailTemplateOutput {

	private Long id;
	private String templateName;
	private String category;
	private String contentHtml;
	private String contentJson;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private Boolean active;
	private String attachmentpath;
	private String description;
	List<FileEntity> inlineImages;
	List<FileEntity> attachments;
}

