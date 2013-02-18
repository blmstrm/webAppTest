package com.blmstrm.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.blmstrm.model.MyEvent;

@Repository
public class MyEventRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void addEvent(int id, String title, String start, String end, boolean allDay){
		MyEvent newEvent = new MyEvent(id,title,start,end,allDay);
		mongoTemplate.insert(newEvent);
	}
	
	public List<MyEvent> getAllEvents(){
		return mongoTemplate.findAll(MyEvent.class);
	}
		
	public void createEventCollection(){
		if(!mongoTemplate.collectionExists(MyEvent.class)){
			mongoTemplate.createCollection(MyEvent.class);
		}
	}
	
	public void dropEventCollection(){
		if(mongoTemplate.collectionExists(MyEvent.class)){
			mongoTemplate.dropCollection(MyEvent.class);
		}
	}
	
}
