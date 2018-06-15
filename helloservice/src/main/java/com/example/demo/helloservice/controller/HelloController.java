package com.example.demo.helloservice.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	@GetMapping(path = "/hello")
	public String getHello() throws Exception{
		int counter = atomicInteger.incrementAndGet();
		logger.info("My increament: {} ", counter);
		return "Hello buddy!!";
	}
}
