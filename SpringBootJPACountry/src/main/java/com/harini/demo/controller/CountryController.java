package com.harini.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harini.demo.dao.CountryRepo;
import com.harini.demo.model.Country;

@RestController
public class CountryController {
	
	@Autowired
	CountryRepo repo;


@GetMapping(path="/")
public List getCountries() {
	
	List<Country> countries = repo.findAll();
	return countries;
	
}

@GetMapping(path="/{countryId}")
public Optional<Country> getcountry(@PathVariable("countryId") int countryId) {
	return repo.findById(countryId);
}

@PostMapping(path="/")
public Country addCountry(@RequestBody Country country) {
	repo.save(country);
	return country;
	
}



}
