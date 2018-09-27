package com.eric.graphql.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eric.graphql.model.Greeting;

@Component
public class GreetingHandler {

	private Map<String, Greeting> greetings;

	public GreetingHandler() {
		super();
		greetings = new HashMap<>();
	}
	
	/**
	 * 寫入一筆
	 * @param greeting
	 * @return
	 */
	public Greeting save(Greeting greeting) {
		String id = UUID.randomUUID().toString();
		greeting.setId(id);
		greetings.put(id, greeting);
		return greeting;
	}
	
	/**
	 * 取得一筆資料
	 * @param id
	 * @return
	 */
	public Greeting find (String id) {
		return greetings.get(id);
	}
	
	public List<Greeting> findAll() {
		if (greetings!=null) {			
			return greetings.values().stream().collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	
}
