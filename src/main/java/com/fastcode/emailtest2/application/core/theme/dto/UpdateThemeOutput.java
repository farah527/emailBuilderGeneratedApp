package com.fastcode.emailtest2.application.core.theme.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateThemeOutput {

  	private String fonts;
  	private String icons;
  	private Long id;
  	private String lightness;
  	private String name;
  	private String palette;

}
