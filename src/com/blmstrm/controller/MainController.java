package com.blmstrm.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import javax.xml.bind.*;

@Controller
public class MainController{

	@Autowired
	MyEventRepository eventRepository;


	private static Calendar  myCal = Calendar.getInstance();

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
		return newEvent;
	}

	/*Update event in database through HTTP.PUT*/
	@RequestMapping(value="/events/{id}",  method = RequestMethod.PUT)
	@ResponseBody
	public MyEvent updateEvent(@PathVariable String id, @RequestBody MyEvent updatedEvent){
		eventRepository.updateEvent(updatedEvent);
		return updatedEvent;
	}

	/*Update event in database through HTTP.DELETE*/
	@RequestMapping(value="/events/{id}",  method = RequestMethod.DELETE)
	@ResponseBody
	public MyEvent removeEvent(@PathVariable String id){
		eventRepository.removeEvent(id);
		return new MyEvent();
	}

	/*Fetch all events from database*/
	@RequestMapping(value="/events", method = RequestMethod.GET)
	@ResponseBody
	public List<MyEvent> getAllEvents(){
		return eventRepository.getAllEvents();
	}

}

