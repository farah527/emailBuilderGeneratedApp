package com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import lombok.Getter;
import lombok.Setter;

import com.fastcode.emailtest2.addons.docmgmt.domain.file.FileEntity;

@Getter @Setter
public class CreateEmailTemplateInput {

	private Boolean active;
	@Length(max = 256, message = "Attachment Path must be less than 256 characters")
	private String attachmentpath;

	@NotNull(message = "Template Name Should not be null")
	@Length(max = 256, message = "Template Name must be less than 256 characters")
    private String templateName;

	@Length(max = 256, message = "Email Category must be less than 256 characters")
    private String category;
	
    @Length(max = 16384, message = "Content Html must be less than 32768 characters")
    private String contentHtml;
	
    @Length(max = 16384, message = "Content Json must be less than 32768 characters")
    private String contentJson;
	
	@NotNull(message = "To Should not be null")
	@Length(max = 256, message = "To must be less than 256 characters")
	@Email(message= "Invalid Email")
    private String to;
	
	@Length(max = 256, message = "CC must be less than 256 characters")
	@Email(message= "Invalid Email")
    private String cc;
	
	@Length(max = 256, message = "Bcc must be less than 256 characters")
	@Email(message= "Invalid Email")
    private String bcc;
	
	@Length(max = 256, message = "Subject must be less than 256 characters")
    private String subject;
    
    @Length(max = 512, message = "Description Name must be less than 512 characters")
	private String description;

	List<FileEntity> inlineImages;
	List<FileEntity> attachments;

}

