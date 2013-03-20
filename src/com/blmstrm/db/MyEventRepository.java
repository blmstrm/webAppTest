package com.blmstrm.db;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.blmstrm.model.MyEvent;

@Repository
public class MyEventRepository{

	@Autowired
	MongoTemplate mongoTemplate;
	
	public MyEvent addEvent(MyEvent newEvent){
		mongoTemplate.insert(newEvent);
		return newEvent;
	}

	public MyEvent updateEvent(MyEvent updatedEvent){
		mongoTemplate.save(updatedEvent);
		return updatedEvent;
	}

	public void removeEvent(String id){
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), MyEvent.class);
	}

	public List<MyEvent> getAllEvents(){
		return mongoTemplate.findAll(MyEvent.class);
	}

}
