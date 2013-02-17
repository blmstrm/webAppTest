package com.blmstrm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MyEvent {

	@Id
	private int id;

	//TODO Add event attributes
	private String title;
	private String start;
	private String end;
	private boolean allDay = false;

	//TODO Instantiate with attributes
	public MyEvent(String ... args){
		//Generate id at creation or get from mongoDB somehow
		this.id= 0 ;
		this.title = args[0];
		this.start = args[1];
		this.end = args[2];
		if(args[3].equals("true")){
			this.allDay = true;
		}
	}

	public String getTitle() {
		return title;
	}

	public String getStart() {
		return start;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public String getEnd() {
		return end;
	}

	public int getId(){
		return id;
	}

}
