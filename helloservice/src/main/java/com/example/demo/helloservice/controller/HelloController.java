package com.example.demo.helloservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping(path = "/hello")
	public String getHello() throws Exception{
		logger.info("Me! Me! Me!");
		return "Hello buddy!";
	}
}
