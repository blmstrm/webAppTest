package com.blmstrm.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
public class MainController{
	
	@Autowired
	MyEventRepository eventRepository;
	
	/*Return calendar view*/
	@RequestMapping(value="/",  method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}

	/*Add event to database through HTTP.POST*/
	@RequestMapping(value="/events",  method = RequestMethod.POST)
	@ResponseBody
	public MyEvent addEvent(@RequestBody MyEvent newEvent){
		eventRepository.addEvent(newEvent);
		System.out.println(newEvent.getId());
		return newEvent;
	}

	/*Update event in database through HTTP.PUT*/
	@RequestMapping(value="/events",  method = RequestMethod.PUT)
	@ResponseBody
	public MyEvent updateEvent(@RequestBody MyEvent currentEvent){
		//Update event
		currentEvent.setTitle("Updated");
		return currentEvent;
	}
	
	//TODO Remove event
	/*Update event in database through HTTP.DELETE*/
	@RequestMapping(value="/events",  method = RequestMethod.DELETE)
	@ResponseBody
	public void removeEvent(@RequestBody MyEvent currentEvent){
		System.out.println("LOG:Removed event!");
	}

	/*Fetch all events from database*/
	@RequestMapping(value="/events", method = RequestMethod.GET)
	@ResponseBody
	public List<MyEvent> getAllEvents(){
		return eventRepository.getAllEvents();
	}

	/*Fetch events from database through HTTP.GET, optionally from date to date.*/
	@RequestMapping(value="/events/{start}/{end}")
	@ResponseBody
	public List<MyEvent> getEventsFromTo(@PathVariable String start, @PathVariable String end){
		//TODO Get events between different dates
		return eventRepository.getAllEvents();
	}

	
}

