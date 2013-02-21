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
	

	public MyEvent addEvent(MyEvent newEvent){
		mongoTemplate.insert(newEvent);
		return newEvent;
	}

	
	public MyEvent getEvent(MyEvent currentEvent){
		//TODO Get event from mongoDB
		return currentEvent;
	}
	
	public MyEvent updateEvent(MyEvent currentEvent){
		//TODO Update in mongoDB.
		return currentEvent;
	}
	
	public void removeEvent(MyEvent currentEvent){
		//TODO Remove event from mongoDB.
	}
	
	public List<MyEvent> getEvents(Long start, Long end){
		//TODO Get events from start to end from mongoDB.
		return mongoTemplate.findAll(MyEvent.class);
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
