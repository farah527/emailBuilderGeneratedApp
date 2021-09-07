package com.fastcode.emailtest2.restcontrollers.core;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.time.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.core.env.Environment;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;
import com.fastcode.emailtest2.application.core.theme.ThemeAppService;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import com.fastcode.emailtest2.domain.core.theme.IThemeRepository;
import com.fastcode.emailtest2.domain.core.theme.Theme;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = "spring.profiles.active=test")
public class ThemeControllerTest {
	
	@Autowired
	protected SortHandlerMethodArgumentResolver sortArgumentResolver;

	@Autowired
	@Qualifier("themeRepository") 
	protected IThemeRepository theme_repository;
	
	@SpyBean
	@Qualifier("themeAppService")
	protected ThemeAppService themeAppService;
	
	@SpyBean
	protected LoggingHelper logHelper;

	@SpyBean
	protected Environment env;

	@Mock
	protected Logger loggerMock;

	protected Theme theme;

	protected MockMvc mvc;
	
	@Autowired
	EntityManagerFactory emf;
	
    static EntityManagerFactory emfs;
    
    static int relationCount = 10;
    
	@PostConstruct
	public void init() {
	emfs = emf;
	}

	@AfterClass
	public static void cleanup() {
		EntityManager em = emfs.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		em.createNativeQuery("truncate table test.theme RESTART IDENTITY").executeUpdate();
	 	em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
		em.getTransaction().commit();
	}
	

	public Theme createEntity() {
	
		Theme themeEntity = new Theme();
  		themeEntity.setFonts("1");
  		themeEntity.setIcons("1");
		themeEntity.setId(1L);
  		themeEntity.setLightness("1");
  		themeEntity.setName("1");
  		themeEntity.setPalette("1");
		themeEntity.setVersiono(0L);
		
		return themeEntity;
	}

	public CreateThemeInput createThemeInput() {
	
	    CreateThemeInput themeInput = new CreateThemeInput();
  		themeInput.setFonts("5");
  		themeInput.setIcons("5");
  		themeInput.setLightness("5");
  		themeInput.setName("5");
  		themeInput.setPalette("5");
		
		return themeInput;
	}

	public Theme createNewEntity() {
		Theme theme = new Theme();
		theme.setFonts("3");
		theme.setIcons("3");
		theme.setId(3L);
		theme.setLightness("3");
		theme.setName("3");
		theme.setPalette("3");
		
		return theme;
	}
	
	public Theme createUpdateEntity() {
		Theme theme = new Theme();
		theme.setFonts("4");
		theme.setIcons("4");
		theme.setId(4L);
		theme.setLightness("4");
		theme.setName("4");
		theme.setPalette("4");
		
		return theme;
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
    
		final ThemeController themeController = new ThemeController(themeAppService,
	logHelper,env);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());

		this.mvc = MockMvcBuilders.standaloneSetup(themeController)
				.setCustomArgumentResolvers(sortArgumentResolver)
				.setControllerAdvice()
				.build();
	}

	@Before
	public void initTest() {

		theme= createEntity();
		List<Theme> list= theme_repository.findAll();
		if(!list.contains(theme)) {
			theme=theme_repository.save(theme);
		}

	}

	@Test
	public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
	
		mvc.perform(get("/theme/" + theme.getId()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}  

	@Test
	public void FindById_IdIsNotValid_ReturnStatusNotFound() {

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/theme/999")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Not found"));

	}
	@Test
	public void CreateTheme_ThemeDoesNotExist_ReturnStatusOk() throws Exception {
		CreateThemeInput themeInput = createThemeInput();	
			

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
	
		String json = ow.writeValueAsString(themeInput);

		mvc.perform(post("/theme").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

	}     

	@Test
	public void DeleteTheme_IdIsNotValid_ThrowEntityNotFoundException() {

        doReturn(null).when(themeAppService).findById(999L);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(delete("/theme/999")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new EntityNotFoundException("There does not exist a theme with a id=999"));

	}  

	@Test
	public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
	
	 	Theme entity =  createNewEntity();
	 	entity.setVersiono(0L);
		entity = theme_repository.save(entity);
		

		FindThemeByIdOutput output= new FindThemeByIdOutput();
		output.setId(entity.getId());
		output.setName(entity.getName());
		
         Mockito.doReturn(output).when(themeAppService).findById(entity.getId());
       
    //    Mockito.when(themeAppService.findById(entity.getId())).thenReturn(output);
        
		mvc.perform(delete("/theme/" + entity.getId()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}  


	@Test
	public void UpdateTheme_ThemeDoesNotExist_ReturnStatusNotFound() throws Exception {
   
        doReturn(null).when(themeAppService).findById(999L);
        
        UpdateThemeInput theme = new UpdateThemeInput();
  		theme.setFonts("999");
  		theme.setIcons("999");
		theme.setId(999L);
  		theme.setLightness("999");
  		theme.setName("999");
  		theme.setPalette("999");

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(theme);

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(put("/theme/999").contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Unable to update. Theme with id=999 not found."));
	}    

	@Test
	public void UpdateTheme_ThemeExists_ReturnStatusOk() throws Exception {
		Theme entity =  createUpdateEntity();
		entity.setVersiono(0L);
		
		entity = theme_repository.save(entity);
		FindThemeByIdOutput output= new FindThemeByIdOutput();
		output.setFonts(entity.getFonts());
		output.setIcons(entity.getIcons());
		output.setId(entity.getId());
		output.setLightness(entity.getLightness());
		output.setName(entity.getName());
		output.setPalette(entity.getPalette());
		output.setVersiono(entity.getVersiono());
		
        Mockito.when(themeAppService.findById(entity.getId())).thenReturn(output);
        
		UpdateThemeInput themeInput = new UpdateThemeInput();
		themeInput.setId(entity.getId());
		themeInput.setName(entity.getName());
		
		
		
		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(themeInput);
	
		mvc.perform(put("/theme/" + entity.getId()+"/").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

		Theme de = createUpdateEntity();
		de.setId(entity.getId());
		theme_repository.delete(de);
		

	}    
	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {

		mvc.perform(get("/theme?search=id[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}    

	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {

		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/theme?search=themeid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property themeid not found!"));

	} 
	
    
}

