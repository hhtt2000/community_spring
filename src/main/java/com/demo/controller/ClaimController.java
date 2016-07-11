package com.demo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.ClaimVO;
import com.demo.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {

	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);
	
	@Inject
	private ClaimService service;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ClaimVO vo) {
		logger.info("claim register...");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.register(vo);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
