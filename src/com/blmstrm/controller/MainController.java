package com.blmstrm.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	/*Add event to database through HTTP.POST*/
	@RequestMapping(value="/event",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public MyEvent addEvent(@RequestBody MyEvent newEvent){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		eventRepository.createEventCollection();

		return eventRepository.addEvent(newEvent);
	}

	/*Update event in database through HTTP.PUT*/
	@RequestMapping(value="/event",  method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public MyEvent updateEvent(@RequestBody MyEvent currentEvent){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);
		
		return eventRepository.updateEvent(currentEvent);
		
	}
	
	//TODO Remove event
	/*Update event in database through HTTP.PUT*/
	@RequestMapping(value="/event",  method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void removeEvent(@RequestBody MyEvent currentEvent){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);
		
		eventRepository.removeEvent(currentEvent);
		
	}
	
	
	/*Fetch events from database through HTTP.GET, optionally from date to date.*/
	@RequestMapping(value="/events/{start}/{end}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MyEvent> getAllEvents(@PathVariable String start, @PathVariable String end){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		//TODO Get events between different dates
		return eventRepository.getAllEvents();

	}

}

