package com.eric.graphql.model;
/**
 * 歡迎
 * @author yung.ho
 *
 */
public class Greeting {

	private String id;
	
	private String message;

	public Greeting() {
		super();
	}

	public Greeting(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
