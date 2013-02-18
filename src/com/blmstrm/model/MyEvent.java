package com.blmstrm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MyEvent {


	//TODO Add event attributes
	//TODO ID used by both mongo and fullCalendar
	private int id;
	private String title;
	
	//These two will overflow on
	//15:30:08 UTC  Sun, 4 December 292,277,026,596
	private long start;
	private long end;
	
	private boolean allDay = false;

	//TODO Instantiate with attributes
	public MyEvent(int id, String title, long start, long end, boolean allDay){
		
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		
	}

	public String getTitle() {
		return title;
	}

	public long getStart() {
		return start;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public long getEnd() {
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
