package com.harini.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harini.demo.Entity.FXRate;
import com.harini.demo.repository.FXRateRepo;

@Service
public class FXRateService {
	
	@Autowired
	FXRateRepo repo;
	
	
	public List<FXRate> getRates() {
		return repo.findAll();
	}
	
	
	public FXRate addRates(FXRate rate) {
		repo.save(rate);
		return rate;
	}
	

	public List<FXRate> getRatesbyBaseToCurrency(String base_currency, String to_currency) {
		
		return repo.findByBaseCurrencyAndToCurrency(base_currency, to_currency);
	}
	

}
