package com.example.demo;
/*
-- Dependency Injection:
 The process of injecting dependent bean objects into target bean objects is called dependency injection.
 Dependency injection is a technique in which an object receives other objects that it depends on, called dependencies.
 Typically, the receiving object is called a client and the passed-in ('injected') object is called a service
 Dependency Injection is a fundamental aspect of the Spring framework, through which
 the Spring container “injects” objects into other objects or “dependencies”.
 This allows for loose coupling(objects don’t depend on one another totally, they can be allowed for a change) 
 of components and moves the responsibility of managing components onto the container
 
 Example: A Car class might need a reference to an Engine class. These required classes are called dependencies,
and in this example the Car class is dependent on having an instance of the Engine class to run.
-- Advantages of DI:
1. To achieve loose coupling(1 object should not totally dependent on the other). 
Example:
Laptop {
HardDrive obj;
Ram ob;
}

We do abstraction (by creating an abstract class or an interface on top of this HardDrive to incorporate any 
changes(multiple hard drive companies like Hitachi, Samsung)
HardDrive obj = new Hitachi(); or samsung();
Here , we shunt hardcode, so the hard drive obj is injected into Laptop. 
This injection is done by the external containers in Spring framework.
Dependency Injection below:
Class Laptop {
@autowired
HardDrive obj;
}

@Component
Class Hitachi implements HardDrive {
}

2. For easy Testing:  Only if our app is loosely coupled testing can be done on the objects without affecting each other. 
For example, a mock obj can be created on the database and tested with the app obj without affecting the database.


-- There are 3 ways to perform DI:
1. CONSTRUCTOR INJECTION:
  Dependency injection is done through constructor]
  This type of injection is safer as the objects won't get created if the dependencies aren't available
  It helps in creating immutable objects because a constructor's signature is 
  the only possible way to create objects. Once we create a bean, we cannot alter its dependencies anymore.
  Also when there are multiple objects to be autowired, contructor injection can be used.
  
2. SETTER INJECTION
 Setter injection is a dependency injection in which the spring framework injects 
 the dependency object using the setter method. 
 Here in the setter method injection, the object is created first and then the dependency is injected.
 
3. FIELD LEVEL OR CLASS LEVEL INJECTION  
 
 
-- @Autowired
Autowiring is a great technique used to reduce the wiring up and configuration of code.
Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection.
To autowire our applications using the Java configuration, we just simply need to add a @Autowired  to our constructor, field, setter method, or config method.
It automatically searches for the object and by default autowire searches for the type not by name of the object.
The default name of the class object in the container is the lowercase of the class name. 
Example : For the class Laptop,  the object name is laptop , the object type is Laptop.class
We can overwrite the class name giving different name at @component (“lap1”)

@Qualifier : the autowire searches for the object by name


 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyFirstSpringBootProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyFirstSpringBootProjectApplication.class, args);
		
		System.out.println("Welcome Spring Boot");
		
		Alien a = context.getBean(Alien.class);
		a.show();
		
		
		Alien2 a2 = context.getBean(Alien2.class);
		a2.show();
		
		Alien3 a3 = context.getBean(Alien3.class);
		a3.show();

	}

}
