package com.springMVC.conference.controller;

import com.springMVC.conference.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration ) // the modelAttribute in form (regis.jsp) is bind together with this Registration object
    {
        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute("registration") Registration registration )
    {
        System.out.println("Registration : " + registration.getName());
        return "redirect:registration";
    }
}
