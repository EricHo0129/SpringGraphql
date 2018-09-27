package com.eric.graphql.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.eric.graphql.handler.GreetingHandler;
import com.eric.graphql.model.Greeting;

@Component
public class GreetingQueryManager implements GraphQLQueryResolver {

	@Autowired
	private GreetingHandler greetingHandler;
	
	public Greeting getGreeting(String id) {
		return greetingHandler.find(id);
	}
	
	public List<Greeting> getAllGreetings() {
		return greetingHandler.findAll();
	}
}
