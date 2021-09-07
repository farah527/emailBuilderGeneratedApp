package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable.dto.*;
import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailVariableTypeEntity;

@Service
public interface IEmailVariableTypeAppService {



	List<EmailVariableTypeEntity> getAllTypes() throws Exception;

}
