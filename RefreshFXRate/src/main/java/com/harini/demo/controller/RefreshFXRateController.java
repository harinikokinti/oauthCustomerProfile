package com.harini.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harini.demo.model.RefreshFXRate;
import com.harini.demo.service.RefreshFXRateService;

@RestController
public class RefreshFXRateController {
	
	@Autowired
	RefreshFXRateService service;
	
	@GetMapping("/")
	public RefreshFXRate getRates() {
	
		return service.getRates();
		
	}
	
	
	
	
	

}
