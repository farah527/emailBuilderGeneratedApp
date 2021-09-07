package com.fastcode.emailtest2.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.emailtest2.restcontrollers.core.ThemeController;
import com.fastcode.emailtest2.application.extended.theme.IThemeAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/theme/extended")
public class ThemeControllerExtended extends ThemeController {

		public ThemeControllerExtended(IThemeAppServiceExtended themeAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		themeAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

