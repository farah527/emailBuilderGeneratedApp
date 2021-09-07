package com.fastcode.emailtest2.application.core.theme;

import org.springframework.data.domain.Pageable;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import com.fastcode.emailtest2.commons.search.SearchCriteria;

import java.util.*;

public interface IThemeAppService {
	
	//CRUD Operations
	
	CreateThemeOutput create(CreateThemeInput theme);

    void delete(Long id);

    UpdateThemeOutput update(Long id, UpdateThemeInput input);

    FindThemeByIdOutput findById(Long id);

    List<FindThemeByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    
    //Join Column Parsers
}

