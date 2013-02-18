package com.blmstrm.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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

	/*URL example - addEvent?title=title&allDay=true&start=1&end=10*/
	@RequestMapping(value="/addEvent", method = RequestMethod.GET)
	public @ResponseBody void addEvent(@RequestParam(value="title",	required=true) String title,
										@RequestParam(value="allDay",required=false) boolean allDay,
										@RequestParam(value="start", required=true) Long start,
										@RequestParam(value="end",required=false)Long end){
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);
		
		eventRepository.createEventCollection();
		
		if(end == null){
			end=0L;
		}
	
		System.out.println("End:"+end);
		
		/*TODO - What can go wrong? Handle eventual error.*/
		eventRepository.addEvent(title, start, end, allDay);
			
	}
	
	@RequestMapping(value="/getEvents", method = RequestMethod.GET)
	public @ResponseBody List <MyEvent> getEvents(@RequestParam(value="start",required=true) Long start,
													@RequestParam(value="end",required=false) Long end) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);
		
		eventRepository.createEventCollection();
	
		return eventRepository.getEvents(start,end);
	}

	@RequestMapping(value="/getAllEvents", method = RequestMethod.GET)
	public @ResponseBody List<MyEvent> getAllEvents(){
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		return eventRepository.getAllEvents();
		
	}
	
	@RequestMapping(value="/clearMongo", method = RequestMethod.GET)
	public @ResponseBody void clearMongo(){
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("MongoConfig.xml");

		MyEventRepository eventRepository = (MyEventRepository)context.getBean(MyEventRepository.class);

		eventRepository.dropEventCollection();
		
	}
	
}

