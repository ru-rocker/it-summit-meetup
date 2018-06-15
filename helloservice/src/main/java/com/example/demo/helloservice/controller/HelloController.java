package com.example.demo.helloservice.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;


@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	@Timed(value = "get.hello.requests", histogram = true, percentiles = { 0.95, 0.99 }, extraTags = { "version","v1" })
	@GetMapping(path = "/hello")
	public String getHello() throws Exception{
		int counter = atomicInteger.incrementAndGet();
		logger.info("My increament: {} ", counter);
		return "Hello buddy!!";
	}
}
