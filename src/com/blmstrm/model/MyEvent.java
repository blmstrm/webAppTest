package com.blmstrm.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public final class MyEvent {
	private String title;
	private String start;
	
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
	
}
