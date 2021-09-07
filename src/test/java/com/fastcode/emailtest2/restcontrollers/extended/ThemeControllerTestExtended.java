package com.fastcode.emailtest2.restcontrollers.extended;

import com.fastcode.emailtest2.restcontrollers.core.ThemeControllerTest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = "spring.profiles.active=test")
public class ThemeControllerTestExtended extends ThemeControllerTest {
	
	//Add your custom code here	
}
