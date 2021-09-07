package com.fastcode.emailtest2.addons.docmgmt.application.file.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class CreateFileInput {

  	private String label;
  	private String name;
  	private String summary;
  	private String versiono;
	private Date created;

}

