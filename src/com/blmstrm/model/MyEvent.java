package com.blmstrm.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MyEvent {



	//TODO Add event attributes
	//TODO ID used by both mongo and fullCalendar
	//private int id;
	private String title;
	private String start;
	private String end;
	
	//TODO Instantiate with attributes
	//TODO Add id attribute
	public MyEvent(String title, String start, String end){
		this.title = title;
		this.start = start;
		this.end = end;
	
		
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
