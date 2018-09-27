package com.eric.graphql.model;

public class Profile {

	private Long pid;
	
	private String name;

	public Profile() {
		super();
	}

	public Profile(Long pid, String name) {
		super();
		this.pid = pid;
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
