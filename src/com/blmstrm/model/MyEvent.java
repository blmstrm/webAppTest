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
	public MyEvent(int id, String title, String start, String end, boolean allDay){
		
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		
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
	
	@Override
	public String toString(){
		return "Event [Title="+title+", start="+start+", end="+end+", isAlldDay="+allDay+", id="+id+"]";
		
	}
}
