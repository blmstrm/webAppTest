package com.blmstrm.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	public List<MyEvent> getEvents(Long start, Long end){
		System.out.println("Inside getEvents"+start+" "+end);
		return mongoTemplate.find(new Query(Criteria.where("start").gte(start).and("end").lte(end).
				orOperator(Criteria.where("start").gte(start).and("end").is(0))), MyEvent.class);
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
