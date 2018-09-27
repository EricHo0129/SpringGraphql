package com.eric.graphql.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.eric.graphql.handler.GreetingHandler;
import com.eric.graphql.model.Greeting;

@Component
public class GreetingMutationManager implements GraphQLMutationResolver {

	@Autowired
	private GreetingHandler greetingHandler;
	
	public Greeting newGreeting(String message) {
		return greetingHandler.save(new Greeting(null, message));
	}
}
