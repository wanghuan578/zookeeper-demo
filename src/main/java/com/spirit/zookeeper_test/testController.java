package com.spirit.zookeeper_test;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@RestController
public class testController {
	
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

	


	
	
	@RequestMapping("/hello")
	public String zk_get() {
		
		



        return "hello";
	}
}
