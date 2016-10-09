package com.test.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.spring.service.ITestSpringService;

@Controller
public class TestSpringController {
	
	@Autowired(required = true)
	@Qualifier("testSpringService")
	ITestSpringService testSpringService;
	
	@RequestMapping(value="/test")
	public ModelAndView handleRequest() {
 
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("message", testSpringService.getMessage());
 
		return mav;
	}

}
