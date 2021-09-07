package com.fastcode.emailtest2.restcontrollers.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fastcode.emailtest2.commons.search.SearchCriteria;
import com.fastcode.emailtest2.commons.search.SearchUtils;
import com.fastcode.emailtest2.commons.search.OffsetBasedPageRequest;
import com.fastcode.emailtest2.application.core.theme.IThemeAppService;
import com.fastcode.emailtest2.application.core.theme.dto.*;
import java.util.*;
import java.time.*;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/theme")
@RequiredArgsConstructor
public class ThemeController {

	@Qualifier("themeAppService")
	@NonNull protected final IThemeAppService _themeAppService;

	@NonNull protected final LoggingHelper logHelper;

	@NonNull protected final Environment env;

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<CreateThemeOutput> create(@RequestBody @Valid CreateThemeInput theme) {
		CreateThemeOutput output=_themeAppService.create(theme);
		return new ResponseEntity(output, HttpStatus.OK);
	}

	// ------------ Delete theme ------------
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {"application/json"})
	public void delete(@PathVariable String id) {

    	FindThemeByIdOutput output = _themeAppService.findById(Long.valueOf(id));
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("There does not exist a theme with a id=%s", id)));

    	_themeAppService.delete(Long.valueOf(id));
    }


	// ------------ Update theme ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<UpdateThemeOutput> update(@PathVariable String id, @RequestBody @Valid UpdateThemeInput theme) {

	    FindThemeByIdOutput currentTheme = _themeAppService.findById(Long.valueOf(id));
		Optional.ofNullable(currentTheme).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to update. Theme with id=%s not found.", id)));


		theme.setVersiono(currentTheme.getVersiono());
	    UpdateThemeOutput output = _themeAppService.update(Long.valueOf(id),theme);
		return new ResponseEntity(output, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<FindThemeByIdOutput> findById(@PathVariable String id) {

    	FindThemeByIdOutput output = _themeAppService.findById(Long.valueOf(id));
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity find(@RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort) throws Exception {

		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);

		return ResponseEntity.ok(_themeAppService.find(searchCriteria,Pageable));
	}

}


