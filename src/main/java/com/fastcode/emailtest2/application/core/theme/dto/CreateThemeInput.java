package com.fastcode.emailtest2.application.core.theme.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateThemeInput {

  	private String fonts;
  
  	private String icons;
  
  	private String lightness;
  
  	@NotNull(message = "name Should not be null")
  	@Length(max = 255, message = "name must be less than 255 characters")
  	private String name;
  
  	private String palette;
  

}

