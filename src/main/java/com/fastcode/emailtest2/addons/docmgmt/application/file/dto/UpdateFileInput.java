package com.fastcode.emailtest2.addons.docmgmt.application.file.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateFileInput {

  	@NotNull(message = "id Should not be null")
  	private Long id;
  	
  	private String label;
  	
  	private String name;

  	private String summary;


}

