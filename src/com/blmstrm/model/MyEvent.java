package com.blmstrm.model;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyEvent {

	private String title;
	private String start;
	private String end;
	private boolean allDay = false;


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
