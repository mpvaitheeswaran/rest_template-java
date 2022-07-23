package com.vaitheeswaran.sstask1.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vaitheeswaran.sstask1.model.User;

import net.minidev.json.JSONObject;

@Component
public class DataSendingClient {

	@Autowired
	RestTemplate restTemplate;
	
	String url = "http://localhost:8080/api/fingress";
	
	public void sendData(User user,String id,String key) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "application/json");
		headers.set("key", key);
		headers.set("id", id);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("firstname", user.getFirstname());
		params.put("lastname", user.getLastname());
		params.put("mobile", user.getMobile());

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
		    url, HttpMethod.POST, requestEntity, String.class, params);
		System.out.println(response);
	}
	public String send2fingress(Map<String,String> user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//headers.set("Accept", "application/json");
		//headers.set("Content-Type", "application/json");
		headers.set("clientid", user.get("clientid"));
		user.remove("clientid");
		headers.set("token", user.get("token"));
		user.remove("token");
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("firstname", user.get("firstname"));
		map.add("lastname", user.get("lastname"));
		map.add("mobile", user.get("mobile"));

		//HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		
		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );

//		ResponseEntity<String> response = restTemplate.exchange(
//		    url, HttpMethod.POST, requestEntity, String.class, user);
		System.out.println(response);
		return response.getBody();
	}
}
