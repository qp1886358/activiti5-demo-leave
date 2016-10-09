package com.test.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LayoutController {
	
	@RequestMapping(value="/leaveProcessMgt/main")
	public ModelAndView leaveProcessMgt() {
 
		ModelAndView mav = new ModelAndView("leaveProcessMgt");
 
		return mav;
	}
	
	@RequestMapping(value="/leaveProcessMgt/center")
	public ModelAndView leaveProcessMgtCenterRegion() {
 
		ModelAndView mav = new ModelAndView("leaveProcessMgtCenterRegion");
 
		return mav;
	}
	
	@RequestMapping(value="/leaveProcessMgt/east")
	public ModelAndView leaveProcessMgtEastRegion() {
 
		ModelAndView mav = new ModelAndView("leaveProcessMgtEastRegion");
 
		return mav;
	}
	
	@RequestMapping(value="/leaveProcessMgt/south")
	public ModelAndView leaveProcessMgtSouthRegion() {
 
		ModelAndView mav = new ModelAndView("leaveProcessMgtSouthRegion");
 
		return mav;
	}
	
	@RequestMapping(value="/leaveProcessMgt/north")
	public ModelAndView leaveProcessMgtNorthRegion() {
 
		ModelAndView mav = new ModelAndView("leaveProcessMgtNorthRegion");
 
		return mav;
	}
	
}
