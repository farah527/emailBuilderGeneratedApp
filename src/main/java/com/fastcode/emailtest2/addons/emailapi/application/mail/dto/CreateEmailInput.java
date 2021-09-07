package com.fastcode.emailtest2.addons.emailapi.application.mail.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import com.fastcode.emailtest2.addons.docmgmt.domain.file.FileEntity;

import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;

@Getter @Setter
public class CreateEmailInput{

	   private static final long serialVersionUID = 1L;
		// Not a good idea to validate email addresses because there are too many
		// variations for an email address
		@NotNull(message = "Id: should not be null")
		private Long id;
		@NotNull(message = "To: should not be null")
		private String to;
		private String cc;
		private String bcc;
		private String subject;
		private String emailBody;
		private String contentJson;
		private Set<FileEntity> inlineImages = new HashSet<FileEntity>();
		private Set<FileEntity> attachments = new HashSet<FileEntity>();
	    
	    
	    private String replyTo;
	    private Date sentDate;
	    private Boolean isHtml;
	
//    @NotNull(message = "To: should not be null")
//    private String to;
//    private String cc;
//    private String bcc;
//    private String subject;
//    private String emailBody;
//    private String replyTo;
//    private Date sentDate;
//    private Boolean isHtml;
//
//    //List of fileIds for inline images and attachments
//    private List<Long> inlineImages;
//    private List<Long> attachments;

}

