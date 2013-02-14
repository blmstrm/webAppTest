package com.blmstrm.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyEvent {
	private int id;
	private String title;
	private Date start;
	private Date end;
	
	public MyEvent(){
		SimpleDateFormat mySpdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
		this.id = 1;
		this.title = "Meeting";
		try {
			this.start = mySpdf.parse("2013-02-14T20:57:56.235-0700");
			this.end = mySpdf.parse("2013-02-14T21:57:56.235-0700");
		} catch (ParseException e) {
			System.err.println("Caught ParserException while parsing dates.");
			e.printStackTrace();
		}

	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}

}
