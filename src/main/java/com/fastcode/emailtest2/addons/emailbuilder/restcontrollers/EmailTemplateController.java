package com.fastcode.emailtest2.addons.emailbuilder.restcontrollers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fastcode.emailtest2.commons.search.SearchCriteria;
import com.fastcode.emailtest2.commons.search.SearchUtils;
import com.fastcode.emailtest2.commons.search.OffsetBasedPageRequest;
import com.fastcode.emailtest2.addons.emailbuilder.application.datasource.dto.FindDataSourceMetaOutputForMapping;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.EmailTemplateAppService;
import com.fastcode.emailtest2.addons.emailbuilder.application.emailtemplate.dto.*;
import com.fastcode.emailtest2.commons.logging.LoggingHelper;
import com.fastcode.emailtest2.commons.domain.EmptyJsonResponse;

import com.fastcode.emailtest2.addons.docmgmt.domain.filehistory.IFileHistoryRepository;
import com.fastcode.emailtest2.addons.emailapi.application.mail.EmailService;
import com.fastcode.emailtest2.addons.docmgmt.domain.file.IFileRepository;

@RestController
@RequestMapping("/email")
public class EmailTemplateController {

	@Autowired
    private EmailTemplateAppService emailTemplateAppService;

	@Autowired
	private LoggingHelper logHelper;

	@Autowired
    private Environment env;
	    
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private IFileRepository filesRepo;

	@Autowired
	private IFileHistoryRepository fileHistoryrepo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CreateEmailTemplateOutput> create(@RequestBody @Valid CreateEmailTemplateInput email) throws IOException {
		FindEmailTemplateByNameOutput foundEmail = emailTemplateAppService.findByName(email.getTemplateName());

		if (foundEmail != null) {
			logHelper.getLogger().error("There already exists a email with a name=%s", email.getTemplateName());
			throw new EntityExistsException(
				String.format("There already exists a user with email address=%s", email.getTemplateName()));
		}
		if(email.getContentJson() != null) {
	       String html= emailTemplateAppService.convertJsonToHtml(email.getContentJson());
		   email.setContentHtml(html);
		   //emailService.sendSimpleMessage(email.getTo(), email.getSubject(),html);
			
		}
	        return new ResponseEntity(emailTemplateAppService.create(email), HttpStatus.CREATED);
	    }

	    // ------------ Delete an email ------------
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
	
		FindEmailTemplateByIdOutput currentEmail = emailTemplateAppService.findById(Long.valueOf(id));
		Optional.ofNullable(currentEmail).orElseThrow(() -> new EntityNotFoundException(String.format("There does not exist a email wth a id=%s", id)));
		
	    emailTemplateAppService.delete(Long.valueOf(id));
	    }
	    // ------------ Update an email ------------

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UpdateEmailTemplateOutput> update(@PathVariable String id, @RequestBody @Valid UpdateEmailTemplateInput email) {
		FindEmailTemplateByIdOutput currentEmail = emailTemplateAppService.findById(Long.valueOf(id));
	   
	    Optional.ofNullable(currentEmail).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to update. Email with id=%s not found.", id)));    
	    
	    return new ResponseEntity(emailTemplateAppService.update(Long.valueOf(id), email), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FindEmailTemplateByIdOutput> findById(@PathVariable String id) {

		FindEmailTemplateByIdOutput currentEmail = emailTemplateAppService.findById(Long.valueOf(id));

	    Optional.ofNullable(currentEmail).orElseThrow(() -> new EntityNotFoundException(String.format("Not found.")));    
	    return new ResponseEntity(currentEmail, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity findAll() {

		return  ResponseEntity.ok(emailTemplateAppService.findAll());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity find(@RequestParam(value = "search", required=false) String search,@RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort) throws Exception {
	   if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
	   if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }
	      //if (sort.isUnsorted()) { sort = new Sort(Sort.Direction.fromString(env.getProperty("fastCode.sort.direction.default")), new String[]{env.getProperty("fastCode.sort.property.default")}); }

	      Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
	      SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);
	      
	  	  return ResponseEntity.ok(emailTemplateAppService.find(searchCriteria,Pageable));
	  }
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public ResponseEntity<FindEmailTemplateByIdOutput> reset(@PathVariable String id) {


		FindEmailTemplateByIdOutput updateEmailTemplateInput = emailTemplateAppService.findByResetId(Long.valueOf(id));
	    if (updateEmailTemplateInput == null) {
	       logHelper.getLogger().error("Unable to reset. Email with id {} not found.", id);
	       return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
	    }

	    resetActualTemplate(updateEmailTemplateInput,id);
	    return new ResponseEntity(updateEmailTemplateInput, HttpStatus.OK);
	}

	public void resetActualTemplate(FindEmailTemplateByIdOutput updateEmailTemplateInput, String id)
	{
	    UpdateEmailTemplateInput email = new UpdateEmailTemplateInput();
	    email.setActive(updateEmailTemplateInput.getActive());
	    email.setAttachmentpath(updateEmailTemplateInput.getAttachmentpath());
	    email.setAttachments(updateEmailTemplateInput.getAttachments());
	    email.setBcc(updateEmailTemplateInput.getBcc());
	    email.setCategory(updateEmailTemplateInput.getCategory());
	    email.setCc(updateEmailTemplateInput.getCc());
	    email.setContentHtml(updateEmailTemplateInput.getContentHtml());
	    email.setContentJson(updateEmailTemplateInput.getContentJson());
	    email.setDescription(updateEmailTemplateInput.getDescription());
	    email.setId(updateEmailTemplateInput.getId());
	    email.setInlineImages(updateEmailTemplateInput.getInlineImages());
	    email.setSubject(updateEmailTemplateInput.getSubject());
	    email.setTemplateName(updateEmailTemplateInput.getTemplateName());
	    email.setTo(updateEmailTemplateInput.getTo());
	    emailTemplateAppService.reset(Long.valueOf(id), email);
	}


	@RequestMapping(value = "/mapping/{emailTemplateId}", method = RequestMethod.GET)
	public ResponseEntity<List<FindDataSourceMetaOutputForMapping>> getMappingForEmail(@PathVariable Long emailTemplateId) {

	    return new ResponseEntity(emailTemplateAppService.getMappingForEmail(emailTemplateId), HttpStatus.OK);
	}


	@RequestMapping(value="/mapping/create",method = RequestMethod.POST)
	public ResponseEntity<CreateEmailTemplateOutput> createMapping(@RequestBody @Valid List<CreateEmailTemplateMappingInput> mapping) throws IOException {

	        return new ResponseEntity(emailTemplateAppService.createMapping(mapping), HttpStatus.CREATED);
	    }

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/deletemapping/{emailTemplateId}", method = RequestMethod.DELETE)
	public void deleteMappingForEmail(@PathVariable Long emailTemplateId) {
	    emailTemplateAppService.deleteMappingForEmail(emailTemplateId);
	}

}


