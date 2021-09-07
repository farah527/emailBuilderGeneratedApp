package com.fastcode.emailtest2.application.core.theme;

import org.mapstruct.Mapper;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import com.fastcode.emailtest2.domain.core.theme.Theme;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IThemeMapper {

   Theme createThemeInputToTheme(CreateThemeInput themeDto);
   CreateThemeOutput themeToCreateThemeOutput(Theme entity);
   
    Theme updateThemeInputToTheme(UpdateThemeInput themeDto);
    
   	UpdateThemeOutput themeToUpdateThemeOutput(Theme entity);

   	FindThemeByIdOutput themeToFindThemeByIdOutput(Theme entity);


}

