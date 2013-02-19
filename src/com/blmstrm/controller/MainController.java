package com.blmstrm.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*POST to add URL*/
	@RequestMapping(value="/events",  method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public MyEvent addEvent(@RequestBody MyEvent newEvent){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		eventRepository.createEventCollection();

		return eventRepository.addEvent(newEvent);
	}

	@RequestMapping(value="/events", method = RequestMethod.GET)
	public @ResponseBody List<MyEvent> getAllEvents(){

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		return eventRepository.getAllEvents();

	}

}

