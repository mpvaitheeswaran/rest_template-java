package com.vaitheeswaran.sstask1.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaitheeswaran.sstask1.client.DataSendingClient;
import com.vaitheeswaran.sstask1.model.User;

@RestController
@RequestMapping(value="api/")
public class DataController {
	@Autowired
	DataSendingClient client;
	//@RequestBody MultiValueMap<String, String> formData
	@RequestMapping(value="/data",method=RequestMethod.POST)
	public User postData(@RequestBody User user,@RequestHeader("id") String id,@RequestHeader("key") String key) {
		client.sendData(user, id, key);
		System.out.println("Key : "+key);
		System.out.println("id : "+id);
		System.out.println(user);
		return user;
	}
	
	@RequestMapping(value="/data2",method=RequestMethod.POST)
	public String postData2(@RequestBody Map<String,String> user) {
		
		String fingressData=client.send2fingress(user);
		System.out.println(fingressData);
		return user.toString();
	}
	
	@RequestMapping(value="/fingress",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postFingress(//@RequestBody Map<String,String> user,
			@RequestBody MultiValueMap<String, String> user,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("token") String token,
			HttpServletRequest request) throws IOException {
		
		System.out.println("Url From Fingress.");
		System.out.println("clientid : "+clientid);
		System.out.println("token : "+token);
		System.out.println("USer :"+user);
//		return user.toString();
		Map<String, String> map = new HashMap<String, String>();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mobile = request.getParameter("mobile");
		

    

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(mobile);
        
		return "Success";
	}
	
}
