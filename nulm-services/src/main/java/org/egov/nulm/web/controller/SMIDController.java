package org.egov.nulm.web.controller;

import javax.validation.Valid;

import org.egov.nulm.model.NULMSMIDRequest;
import org.egov.nulm.model.ResponseInfoWrapper;
import org.egov.nulm.service.SEPService;
import org.egov.nulm.service.SMIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/smid")
public class SMIDController {

	private final SMIDService service;

	@Autowired
	public SMIDController(SMIDService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/_create")
	public ResponseEntity<ResponseInfoWrapper> createSMIDApplication(@Valid @RequestBody NULMSMIDRequest seprequest) {
		return service.createSMIDApplication(seprequest);
	}
	
	
   
}
