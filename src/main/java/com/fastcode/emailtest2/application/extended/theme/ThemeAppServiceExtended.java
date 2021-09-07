package com.fastcode.emailtest2.application.extended.theme;

import org.springframework.stereotype.Service;
import com.fastcode.emailtest2.application.core.theme.ThemeAppService;

import com.fastcode.emailtest2.domain.extended.theme.IThemeRepositoryExtended;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;

@Service("themeAppServiceExtended")
public class ThemeAppServiceExtended extends ThemeAppService implements IThemeAppServiceExtended {

	public ThemeAppServiceExtended(IThemeRepositoryExtended themeRepositoryExtended,
				IThemeMapperExtended mapper,LoggingHelper logHelper) {

		super(themeRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

