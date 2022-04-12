package com.harini.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("call")
	public ModelAndView home(Alien alien) {    //  http://localhost:8080/call?aid=1&aName=harini&lang=telugu
		ModelAndView mv = new ModelAndView();  // jsp can  be passed in the constructor too as  new ModelAndView("home")
		mv.addObject("keyObj",alien);
		// mv.addObject(alien);  if toString is present in the Alien model, in jsp , ${alien}
		mv.setViewName("home");	
		return mv;
	}
	/*
	
	@RequestMapping("call")
	public ModelAndView home(@RequestParam("name")String myName) { 
		ModelAndView mv = new ModelAndView();
		mv.addObject("attributeName",myName);  //  add the data(model) into the key and pass into the view
		mv.setViewName("home");  // Set a view name for this ModelAndView, to be resolved by the DispatcherServlet via 
								 // a ViewResolver. Will override any pre-existing view name or View
		return mv;
	}  */
	
	/*
	@RequestMapping("call")
	public String home(@RequestParam("name")String myName, HttpSession session)
	 {          // without req & res objects, we can get session object through DI by spring
	 // @RequestParam to extract query parameters, form parameters, and even files from the request.
	// here it maps name = myName	
		System.out.println("Hi " + myName);
		session.setAttribute("attributeName", myName);	
		return "home";
	}
	*/
	
	/*
	@RequestMapping("call")
	public String home(HttpServletRequest req) {
		//System.out.println("Hello");
		String reqName = req.getParameter("name");
		System.out.println("Hi " + reqName);	
		
		HttpSession session = req.getSession();  // create a session object
		session.setAttribute("attributeName", reqName);	// get name details from URL	
		
		return "home";  // calling home.jsp
	}  */

}
