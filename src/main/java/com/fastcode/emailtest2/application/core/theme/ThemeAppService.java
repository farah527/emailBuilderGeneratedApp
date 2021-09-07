package com.fastcode.emailtest2.application.core.theme;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import com.fastcode.emailtest2.domain.core.theme.IThemeRepository;
import com.fastcode.emailtest2.domain.core.theme.QTheme;
import com.fastcode.emailtest2.domain.core.theme.Theme;
import com.fastcode.emailtest2.commons.search.*;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

@Service("themeAppService")
@RequiredArgsConstructor
public class ThemeAppService implements IThemeAppService {

	@Qualifier("themeRepository")
	@NonNull protected final IThemeRepository _themeRepository;

	@Qualifier("IThemeMapperImpl")
	@NonNull protected final IThemeMapper mapper;

	@NonNull protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
	public CreateThemeOutput create(CreateThemeInput input) {

		Theme theme = mapper.createThemeInputToTheme(input);

		Theme createdTheme = _themeRepository.save(theme);
		return mapper.themeToCreateThemeOutput(createdTheme);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateThemeOutput update(Long themeId, UpdateThemeInput input) {

		Theme existing = _themeRepository.findById(themeId).get();

		Theme theme = mapper.updateThemeInputToTheme(input);
		
		Theme updatedTheme = _themeRepository.save(theme);
		return mapper.themeToUpdateThemeOutput(updatedTheme);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long themeId) {

		Theme existing = _themeRepository.findById(themeId).orElse(null); 
	 	
	 	_themeRepository.delete(existing);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public FindThemeByIdOutput findById(Long themeId) {

		Theme foundTheme = _themeRepository.findById(themeId).orElse(null);
		if (foundTheme == null)  
			return null; 
 	   
 	    return mapper.themeToFindThemeByIdOutput(foundTheme);
	}
	
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<FindThemeByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception  {

		Page<Theme> foundTheme = _themeRepository.findAll(search(search), pageable);
		List<Theme> themeList = foundTheme.getContent();
		Iterator<Theme> themeIterator = themeList.iterator(); 
		List<FindThemeByIdOutput> output = new ArrayList<>();

		while (themeIterator.hasNext()) {
		Theme theme = themeIterator.next();
 	    output.add(mapper.themeToFindThemeByIdOutput(theme));
		}
		return output;
	}
	
	protected BooleanBuilder search(SearchCriteria search) throws Exception {

		QTheme theme= QTheme.theme;
		if(search != null) {
			Map<String,SearchFields> map = new HashMap<>();
			for(SearchFields fieldDetails: search.getFields())
			{
				map.put(fieldDetails.getFieldName(),fieldDetails);
			}
			List<String> keysList = new ArrayList<String>(map.keySet());
			checkProperties(keysList);
			return searchKeyValuePair(theme, map,search.getJoinColumns());
		}
		return null;
	}
	
	protected void checkProperties(List<String> list) throws Exception  {
		for (int i = 0; i < list.size(); i++) {
			if(!(
				list.get(i).replace("%20","").trim().equals("fonts") ||
				list.get(i).replace("%20","").trim().equals("icons") ||
				list.get(i).replace("%20","").trim().equals("id") ||
				list.get(i).replace("%20","").trim().equals("lightness") ||
				list.get(i).replace("%20","").trim().equals("name") ||
				list.get(i).replace("%20","").trim().equals("palette")
			)) 
			{
			 throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!" );
			}
		}
	}
	
	protected BooleanBuilder searchKeyValuePair(QTheme theme, Map<String,SearchFields> map,Map<String,String> joinColumns) {
		BooleanBuilder builder = new BooleanBuilder();
        
		for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if(details.getKey().replace("%20","").trim().equals("fonts")) {
				if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.fonts.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals")) {
					builder.and(theme.fonts.eq(details.getValue().getSearchValue()));
				} else if(details.getValue().getOperator().equals("notEqual")) {
					builder.and(theme.fonts.ne(details.getValue().getSearchValue()));
				}
			}
            if(details.getKey().replace("%20","").trim().equals("icons")) {
				if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.icons.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals")) {
					builder.and(theme.icons.eq(details.getValue().getSearchValue()));
				} else if(details.getValue().getOperator().equals("notEqual")) {
					builder.and(theme.icons.ne(details.getValue().getSearchValue()));
				}
			}
			 if(details.getKey().replace("%20","").trim().equals("id")) {
			 	if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.id.like(details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals") && StringUtils.isNumeric(details.getValue().getSearchValue())) {
					builder.and(theme.id.eq(Long.valueOf(details.getValue().getSearchValue())));
				} else if(details.getValue().getOperator().equals("notEqual") && StringUtils.isNumeric(details.getValue().getSearchValue())) {
					builder.and(theme.id.ne(Long.valueOf(details.getValue().getSearchValue())));
				} else if(details.getValue().getOperator().equals("range")) {
				  	if(StringUtils.isNumeric(details.getValue().getStartingValue()) && StringUtils.isNumeric(details.getValue().getEndingValue())) {
                	   builder.and(theme.id.between(Long.valueOf(details.getValue().getStartingValue()), Long.valueOf(details.getValue().getEndingValue())));
                   	} else if(StringUtils.isNumeric(details.getValue().getStartingValue())) {
                	  	builder.and(theme.id.goe(Long.valueOf(details.getValue().getStartingValue())));
                   	} else if(StringUtils.isNumeric(details.getValue().getEndingValue())) {
                	  	builder.and(theme.id.loe(Long.valueOf(details.getValue().getEndingValue())));
					}
				}
			}
            if(details.getKey().replace("%20","").trim().equals("lightness")) {
				if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.lightness.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals")) {
					builder.and(theme.lightness.eq(details.getValue().getSearchValue()));
				} else if(details.getValue().getOperator().equals("notEqual")) {
					builder.and(theme.lightness.ne(details.getValue().getSearchValue()));
				}
			}
            if(details.getKey().replace("%20","").trim().equals("name")) {
				if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.name.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals")) {
					builder.and(theme.name.eq(details.getValue().getSearchValue()));
				} else if(details.getValue().getOperator().equals("notEqual")) {
					builder.and(theme.name.ne(details.getValue().getSearchValue()));
				}
			}
            if(details.getKey().replace("%20","").trim().equals("palette")) {
				if(details.getValue().getOperator().equals("contains")) {
					builder.and(theme.palette.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				} else if(details.getValue().getOperator().equals("equals")) {
					builder.and(theme.palette.eq(details.getValue().getSearchValue()));
				} else if(details.getValue().getOperator().equals("notEqual")) {
					builder.and(theme.palette.ne(details.getValue().getSearchValue()));
				}
			}
	    
		}
		
		return builder;
	}
	

}



