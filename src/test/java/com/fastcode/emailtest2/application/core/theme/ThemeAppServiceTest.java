package com.fastcode.emailtest2.application.core.theme;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcode.emailtest2.domain.core.theme.*;
import com.fastcode.emailtest2.commons.search.*;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import com.fastcode.emailtest2.domain.core.theme.QTheme;
import com.fastcode.emailtest2.domain.core.theme.Theme;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.time.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ThemeAppServiceTest {

	@InjectMocks
	@Spy
	protected ThemeAppService _appService;

	@Mock
	protected IThemeRepository _themeRepository;
	
	@Mock
	protected IThemeMapper _mapper;

	@Mock
	protected Logger loggerMock;

	@Mock
	protected LoggingHelper logHelper;
	
    protected static Long ID=15L;
	 
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(_appService);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());
	}
	
	@Test
	public void findThemeById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
		Optional<Theme> nullOptional = Optional.ofNullable(null);
		Mockito.when(_themeRepository.findById(anyLong())).thenReturn(nullOptional);
		Assertions.assertThat(_appService.findById(ID )).isEqualTo(null);
	}
	
	@Test
	public void findThemeById_IdIsNotNullAndIdExists_ReturnTheme() {

		Theme theme = mock(Theme.class);
		Optional<Theme> themeOptional = Optional.of((Theme) theme);
		Mockito.when(_themeRepository.findById(anyLong())).thenReturn(themeOptional);
		
	    Assertions.assertThat(_appService.findById(ID )).isEqualTo(_mapper.themeToFindThemeByIdOutput(theme));
	}
	
	
	@Test 
    public void createTheme_ThemeIsNotNullAndThemeDoesNotExist_StoreTheme() { 
 
        Theme theme = mock(Theme.class); 
    	CreateThemeInput themeInput = new CreateThemeInput();
		
        Mockito.when(_mapper.createThemeInputToTheme(any(CreateThemeInput.class))).thenReturn(theme); 
        Mockito.when(_themeRepository.save(any(Theme.class))).thenReturn(theme);

	   	Assertions.assertThat(_appService.create(themeInput)).isEqualTo(_mapper.themeToCreateThemeOutput(theme));

    } 
	@Test
	public void updateTheme_ThemeIdIsNotNullAndIdExists_ReturnUpdatedTheme() {

		Theme themeEntity = mock(Theme.class);
		UpdateThemeInput theme= mock(UpdateThemeInput.class);
		
		Optional<Theme> themeOptional = Optional.of((Theme) themeEntity);
		Mockito.when(_themeRepository.findById(anyLong())).thenReturn(themeOptional);
	 		
		Mockito.when(_mapper.updateThemeInputToTheme(any(UpdateThemeInput.class))).thenReturn(themeEntity);
		Mockito.when(_themeRepository.save(any(Theme.class))).thenReturn(themeEntity);
		Assertions.assertThat(_appService.update(ID,theme)).isEqualTo(_mapper.themeToUpdateThemeOutput(themeEntity));
	}
    
	@Test
	public void deleteTheme_ThemeIsNotNullAndThemeExists_ThemeRemoved() {

		Theme theme = mock(Theme.class);
		Optional<Theme> themeOptional = Optional.of((Theme) theme);
		Mockito.when(_themeRepository.findById(anyLong())).thenReturn(themeOptional);
 		
		_appService.delete(ID); 
		verify(_themeRepository).delete(theme);
	}
	
	@Test
	public void find_ListIsEmpty_ReturnList() throws Exception {

		List<Theme> list = new ArrayList<>();
		Page<Theme> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindThemeByIdOutput> output = new ArrayList<>();
		SearchCriteria search= new SearchCriteria();

		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
		Mockito.when(_themeRepository.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void find_ListIsNotEmpty_ReturnList() throws Exception {

		List<Theme> list = new ArrayList<>();
		Theme theme = mock(Theme.class);
		list.add(theme);
    	Page<Theme> foundPage = new PageImpl(list);
		Pageable pageable = mock(Pageable.class);
		List<FindThemeByIdOutput> output = new ArrayList<>();
        SearchCriteria search= new SearchCriteria();

		output.add(_mapper.themeToFindThemeByIdOutput(theme));
		
		Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
    	Mockito.when(_themeRepository.findAll(any(Predicate.class),any(Pageable.class))).thenReturn(foundPage);
		Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
	}
	
	@Test
	public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
		QTheme theme = QTheme.theme;
	    SearchFields searchFields = new SearchFields();
		searchFields.setOperator("equals");
		searchFields.setSearchValue("xyz");
	    Map<String,SearchFields> map = new HashMap<>();
        map.put("fonts",searchFields);
		Map<String,String> searchMap = new HashMap<>();
        searchMap.put("xyz",String.valueOf(ID));
		BooleanBuilder builder = new BooleanBuilder();
        builder.and(theme.fonts.eq("xyz"));
		Assertions.assertThat(_appService.searchKeyValuePair(theme,map,searchMap)).isEqualTo(builder);
	}
	
	@Test (expected = Exception.class)
	public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("xyz");
		_appService.checkProperties(list);
	}
	
	@Test
	public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
		List<String> list = new ArrayList<>();
        list.add("fonts");
        list.add("icons");
        list.add("lightness");
        list.add("name");
        list.add("palette");
		_appService.checkProperties(list);
	}
	
	@Test
	public void search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
	
		Map<String,SearchFields> map = new HashMap<>();
		QTheme theme = QTheme.theme;
		List<SearchFields> fieldsList= new ArrayList<>();
		SearchFields fields=new SearchFields();
		SearchCriteria search= new SearchCriteria();
		search.setType(3);
		search.setValue("xyz");
		search.setOperator("equals");
        fields.setFieldName("fonts");
        fields.setOperator("equals");
		fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
		BooleanBuilder builder = new BooleanBuilder();
        builder.or(theme.fonts.eq("xyz"));
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
		Mockito.doReturn(builder).when(_appService).searchKeyValuePair(any(QTheme.class), any(HashMap.class), any(HashMap.class));
        
		Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
	}
	
	@Test
	public void search_StringIsNull_ReturnNull() throws Exception {

		Assertions.assertThat(_appService.search(null)).isEqualTo(null);
	}
	
}


