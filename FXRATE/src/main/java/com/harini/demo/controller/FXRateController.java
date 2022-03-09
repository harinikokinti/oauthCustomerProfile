package com.harini.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harini.demo.Entity.FXRate;
import com.harini.demo.service.FXRateService;

@RestController
public class FXRateController {
	
	@Autowired
	FXRateService service;
	
	@GetMapping("/")
	public List<FXRate> getRates() {	
		
		return service.getRates();
		
	}
	
	@PostMapping("/")
	public FXRate addRates(@RequestBody FXRate rate) {
		service.addRates(rate);
		return rate;
	}
	
	
	@GetMapping(path="/get/{base_currency}/{to_currency}")
	public List<FXRate> getRatesbyBaseToCurrency(@PathVariable("base_currency") String base_currency, 
			@PathVariable("to_currency") String to_currency) {	
		
		System.out.println( service.getRatesbyBaseToCurrency(base_currency,to_currency));
		return service.getRatesbyBaseToCurrency(base_currency,to_currency);
		
	}
	
	
	
		

}
