package com.fastcode.emailtest2.addons.emailbuilder.application.emailvariable;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IMasterEntityService {

	List<String> getMastersByMasterName(String name);

}
