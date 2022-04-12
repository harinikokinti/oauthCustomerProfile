package com.example.demo;

/*
 Setter injection 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alien3 {
	 
	Laptop laptop;
	
	@Autowired
	void setLaptop(Laptop laptop) {
		System.out.print("This is dependency injection through setter method" );
		this.laptop = laptop;
	}
	
	Laptop getLaptop() {
		return laptop;
	}
	
	public void show() {
		System.out.println("In show method - Alien 3");
		laptop.compile();
	}
	

}
