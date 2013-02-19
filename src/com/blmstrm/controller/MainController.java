package com.blmstrm.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blmstrm.db.MyEventRepository;
import com.blmstrm.model.MyEvent;

@Controller
public class MainController {

	/*Return calendar view*/
	@RequestMapping(value="/",  method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}

	/*Add events to database through HTTP.POST*/
	@RequestMapping(value="/events",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public MyEvent addEvent(@RequestBody MyEvent newEvent){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		eventRepository.createEventCollection();

		return eventRepository.addEvent(newEvent);
	}

	//TODO Update event
	//TODO Remove event
	
	/*Fetch events from database through HTTP.GET*/
	@RequestMapping(value="/events", method = RequestMethod.GET)
	public @ResponseBody List<MyEvent> getAllEvents(){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		return eventRepository.getAllEvents();

	}

}

