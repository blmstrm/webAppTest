package com.blmstrm.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.blmstrm.model.MyEvent;

@Repository
public class MyEventRepository {

	@Autowired
	MongoTemplate mngTemplate;
	
	public void addEvent(){
		MyEvent newEvent = new MyEvent();
		mngTemplate.insert(newEvent);
	}
	
	public List<MyEvent> getAllEvents(){
		return mngTemplate.findAll(MyEvent.class);
	}
		
	public void createEventCollection(){
		if(mngTemplate.collectionExists(MyEvent.class)){
			mngTemplate.createCollection(MyEvent.class);
		}
	}
	
	public void dropEventCollection(){
		if(mngTemplate.collectionExists(MyEvent.class)){
			mngTemplate.dropCollection(MyEvent.class);
		}
	}
	
}
