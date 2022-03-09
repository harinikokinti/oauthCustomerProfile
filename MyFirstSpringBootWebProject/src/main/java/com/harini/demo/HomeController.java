package com.harini.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("call")
	public ModelAndView home(Alien alien) { 
		ModelAndView mv = new ModelAndView();
		mv.addObject("keyObj",alien);
		mv.setViewName("home");	
		return mv;
	}
	/*
	
	@RequestMapping("call")
	public ModelAndView home(@RequestParam("name")String myName) { 
		ModelAndView mv = new ModelAndView();
		mv.addObject("attributeName",myName);  //  add the data into the key and pass into the view
		mv.setViewName("home");  // Set a view name for this ModelAndView, to be resolved by the DispatcherServlet via 
								 // a ViewResolver. Will override any pre-existing view name or View
		return mv;
	}  */
	
	/*
	@RequestMapping("call")
	public String home(@RequestParam("name")String myName, HttpSession session) { 
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
		
		HttpSession session = req.getSession(); // get name details from URL
		session.setAttribute("attributeName", reqName);		
		
		return "home";  // calling home.jsp
	}  */

}
