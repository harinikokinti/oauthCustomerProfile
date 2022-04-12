package com.example.demo;

/*
 * Constructor injection
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alien2 {
	
	Laptop laptop;
	
	
	@Autowired
	Alien2(Laptop laptop) {
		System.out.print("This is dependency injection through constructor" );
		this.laptop =laptop;		
	}
	
	public void show() {
		System.out.println("In show method - Alien 2");
		laptop.compile();
	}


}
