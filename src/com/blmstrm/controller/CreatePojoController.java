package com.blmstrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.blmstrm.model.MyPojo;


@Controller
public class CreatePojoController {
	
	@RequestMapping(value="createPojo/{pojoName}/{pojoNumber}", method = RequestMethod.GET)
	public @ResponseBody MyPojo getJSONPojo(@PathVariable String pojoName, @PathVariable int pojoNumber) {
		MyPojo returnPojo = new MyPojo();
		returnPojo.setPojoName(pojoName);
		returnPojo.setPojoNumber(pojoNumber);
		
		return returnPojo;
	}
}
