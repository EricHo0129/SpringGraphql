package com.eric.graphql.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.eric.graphql.model.Profile;

@Component
public class ProfileQueryManager implements GraphQLQueryResolver {

	private static final Map<Long, Profile> profileMap = new HashMap<>();
	
	static {
		profileMap.put(100024L, new Profile(100024L,"卡卡西"));
		profileMap.put(100080L, new Profile(100080L,"陳佳鑫"));
		profileMap.put(100008L, new Profile(100008L,"吳大大"));
	}
	
	public Profile getProfile(Long pid) {
		if (!profileMap.containsKey(pid)) {
			throw new RuntimeException(String.format("Pid [%s] not found.", pid));
		}
		return profileMap.get(pid);
	}
}
