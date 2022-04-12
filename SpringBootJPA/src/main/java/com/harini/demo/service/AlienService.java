package com.harini.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harini.demo.dao.AlienRepo;
import com.harini.demo.model.Alien;
import com.harini.demo.model.Country;

@Service
public class AlienService {

	private RestTemplate countryRestTemplate;
	private AlienRepo repo;

	@Autowired
	public AlienService(RestTemplate countryRestTemplate, AlienRepo repo) {
		super();
		this.countryRestTemplate = countryRestTemplate;
		this.repo = repo;

	}

	public Alien getAlien(int aid) {

		Alien alien = repo.findById(aid).orElse(null);

		Country country = countryRestTemplate
				.getForEntity("http://localhost:8090/countries/" + alien.getCountryId(), Country.class).getBody();
		
		alien.setCountry(country);

		return alien;
	}
	
	
	public List<Alien> getAliens() {
		List<Alien> aliens = repo.findAll();
		
		for(Alien alien : aliens) {
			
			Country country = countryRestTemplate
					.getForEntity("http://localhost:8090/countries" + alien.getCountryId(), Country.class).getBody();
			alien.setCountry(country);
			aliens.add(alien);
		}
		aliens.forEach(System.out::print);
		
		return aliens;
		
	}

}
