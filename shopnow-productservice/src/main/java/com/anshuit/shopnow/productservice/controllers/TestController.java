package com.anshuit.shopnow.productservice.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {

	@Value("${app.environment}")
	private String appEnvironment;

	@Autowired
	private Environment environment;

	@GetMapping("/test")
	public ResponseEntity<HashMap<String, String>> testEndPoint() {
		LinkedHashMap<String, String> testHashMap = new LinkedHashMap<>();
		testHashMap.put("APP-ENV [ Reading From @Value() Property - Under Refresh Scope ]", appEnvironment);
		testHashMap.put("APP-NAME", environment.getProperty("spring.application.name"));
		testHashMap.put("APP-MAVEN-HOME", environment.getProperty("MAVEN_HOME"));
		testHashMap.put("APP-JAVA-HOME", environment.getProperty("JAVA_HOME"));
		testHashMap.put("APP-JAVA-VERSION", System.getProperty("java.version"));
		return new ResponseEntity<>(testHashMap, HttpStatus.OK);
	}
}
