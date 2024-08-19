package com.springboot.hello.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

		//http://localhost:9000/api/v1/get-api/hello
		@RequestMapping(value="/hello", method= RequestMethod.GET)
		public String getHello() {
			return "Hello World";
		}
		
		@RequestMapping(value="/hello1", method=RequestMethod.GET )
		public String gethello() {
			return "Hello World";
		}
		
		//http://localhost:9000/api/v1/get-api/name	
		@GetMapping(value="/name")
		public String getName() {
			return "Flature";
		}
		
		//http://localhost:9000/api/v1/get-api/variable1/{String 값}
		@GetMapping(value="/variable1/{variable}")
		public String getVariable1(@PathVariable String variable) {
			return variable;
		}
		
		//http://localhost:9000/api/v1/get-api/request1?name=value1&email=value&organization=value3
		@GetMapping(value="/reqeust1")
		public String getRequestParam1(
				@RequestParam String name, 
				@RequestParam String email,
				@RequestParam String organization) {
				return name + "" + email + "" + organization;
		}
		
		//http://localhost:9000/api/v1/get-api/request2?key1=value&key2=value2
		@GetMapping(value="/request2")
		public void getReqeustParam2(@RequestParam Map<String, String> param) {
			StringBuilder sb = new StringBuilder();
		}
		
				
}
