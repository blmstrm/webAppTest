package com.blmstrm.model;


import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	public MyEvent(){
		SimpleDateFormat mySpdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		this.title = "Meeting";
		this.start = mySpdf.format(calendar.getTime());
		calendar.add(Calendar.HOUR_OF_DAY,3);
		this.end = mySpdf.format(calendar.getTime());
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


}
