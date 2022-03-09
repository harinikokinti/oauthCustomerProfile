package com.harini.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harini.demo.dao.AlienRepo;
import com.harini.demo.model.Alien;
import com.harini.demo.service.AlienService;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@Autowired
	AlienService alienService;
	
	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
		
	}
	
	// REST API
	
	@PostMapping(path="/alien", consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {   // send the data from the client
											// @RequestBody, if we send raw data in JSON format (from Postman API)
		repo.save(alien);  // save the data into the repository
		
		return alien;  // return the same data back to the server 
	}
	
	
	@GetMapping(path="/alien")
	public List<Alien> getAliens() {
		
		return repo.findAll();
		
	}
	
	
	@GetMapping(path="/alien/{aid}")
	public Alien getAlien(@PathVariable("aid") int aid) {
		
		return alienService.getAlien(aid);
		
	}	
	
//	@SuppressWarnings("deprecation")
	@DeleteMapping(path="/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		
		repo.deleteById(aid);
		
		return "deleted";
		
	}
	
	
	@PutMapping(path="/alien" , consumes= {"application/json"})
	public Alien saveOrUpdate(@RequestBody Alien alien) {   
											
		repo.save(alien);  		
		return alien;  
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Data seen on the browser in JSON format by default. It is not REST application
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		
		return "home.jsp";
	}
	
//	@RequestMapping(path= "/aliens", produces= {"application/xml"})  //  to restrict spring boot to support only XML format from server side
	// here produces means server produces data to the client, if consumes, the server consumes data from the client
	@GetMapping("/aliens")
	@ResponseBody   
	public List<Alien> getAliens() {
		
		return repo.findAll();
		
	}
	
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
		
	}	
	
	*/
	
	
	
	
	/*  //  Data in the view form [ MVC ]
	
	@RequestMapping("/getAlienById")
	public ModelAndView getAlienById(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Optional<Alien> alien = repo.findById(aid);  //  here Optional is used to handle null values	
		
		System.out.println(repo.findByLang("Telugu"));  // get records by language
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByLangSorted("Telugu")); //  manual query
		
		mv.addObject(alien.orElseGet(null));
		return mv;
	}
	
	
	@RequestMapping("/getAliens")
	public ModelAndView getAliens() {
		
		ModelAndView mv = new ModelAndView("showAliens.jsp");
		Iterable<Alien> alienIterable = repo.findAll() ; 
		
		ArrayList<Alien> aliens = new ArrayList<Alien>();		
		
		// for(Alien alien:alienIterable) {  // convert iterable to list using for loop
		//	aliens.add(alien); 	} 
		
		alienIterable.forEach(aliens::add);  // convert iterable to list using functional interface 
				
		mv.addObject(aliens);
		
		return mv;
	}   
	 */
	
	
	
	

}
