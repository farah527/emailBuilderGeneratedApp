package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.dto.*;

@Service
public interface IEmailCategoryService {

	List<String> getAllCategories() throws Exception;

}
