package com.blmstrm.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.blmstrm.model.MyEvent;


@Controller
public class MainController {
	
	@RequestMapping(value="/",  method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/getEvent", method = RequestMethod.GET)
	public @ResponseBody MyEvent getEvent() {
		return  new MyEvent();
	}
}
