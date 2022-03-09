package com.example.demo;

/*
 @Autowired
Autowiring is a great technique used to reduce the wiring up and configuration of code.
Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection.
To autowire our applications using the Java configuration, we just simply need to add a @Autowired  to our constructor, field, setter method, or config method.
It automatically searches for the object and by default autowire searches for the type not by name of the object.
The default name of the class object in the container is the lowercase of the class name. 
Example : For the class Laptop,  the object name is laptop , the object type is Laptop.class
We can overwrite the class name giving different name at @component (“lap1”)


@Qualifier : the autowire searches for the object by type 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value="prototype")
public class Alien {
	
	private int aid;
	private String aname;
	private String tech;
	@Autowired      //  it automatically searches for the Laptop.class (laptop object) in the container by its type 
	@Qualifier("laptop")   // it searches based on the object name (here for Laptop, the default name is laptop, which can be overwritten at @Component(value="lap1}
	private Laptop laptop;	

	
	public Alien() {
		super();
		System.out.println("Object Created");
	}
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	
	
	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	public void show() {
		System.out.println("In show method");
		laptop.compile();
	}

}
