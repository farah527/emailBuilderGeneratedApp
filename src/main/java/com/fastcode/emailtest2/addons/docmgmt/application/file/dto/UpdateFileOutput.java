package com.fastcode.emailtest2.addons.docmgmt.application.file.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class UpdateFileOutput {

  	private Long ancestorId;
  	private Long ancestralRootId;
  	private String contentId;
  	private Long contentLength;
  	private Date created;
  	private Long id;
  	private String label;
  	private String lockOwner;
  	private String mimeType;
  	private String name;
  	private Long successorId;
  	private String summary;

}

