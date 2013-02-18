package com.blmstrm.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blmstrm.db.MyEventRepository;
import com.blmstrm.model.MyEvent;

@Controller
public class MainController {
	
	@RequestMapping(value="/",  method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/getAllEvents", method = RequestMethod.GET)
	public @ResponseBody List <MyEvent> getAllEvents() {
						
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);
		
		eventRepository.dropEventCollection();
		
		eventRepository.createEventCollection();
		
		//Insert one temporary event
		eventRepository.addEvent(0,"Meeting","Start","End",true);
		
		//Return all events
		return  eventRepository.getAllEvents();
	}
}
