package com.eric.graphql.config;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.SchemaParser;
import com.eric.graphql.manager.GreetingMutationManager;
import com.eric.graphql.manager.GreetingQueryManager;
import com.eric.graphql.manager.ProfileQueryManager;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;

@Configuration
public class ServletConfig {
	
	@Bean
	public ServletRegistrationBean<Servlet> greetingGraphQLBean(GreetingQueryManager greetingQueryManager, GreetingMutationManager greetingMutationManager) {
		GraphQLSchema schema = SchemaParser.newParser().files("root.graphqls","greeting.graphqls").
				resolvers(greetingQueryManager,greetingMutationManager).
				build().makeExecutableSchema();
		ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<Servlet>(SimpleGraphQLHttpServlet.newBuilder(schema).build(), "/mysecond");
		bean.setName("greetingGraphQLBean"); //一定不能與其他的ServletRegistrationBean重複,否則不能註冊
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<Servlet> profileGraphQLBean(ProfileQueryManager profileQueryManager) {
		GraphQLSchema schema = SchemaParser.newParser().file("profile.graphqls").
				resolvers(profileQueryManager).
				build().makeExecutableSchema();
		ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<Servlet>(SimpleGraphQLHttpServlet.newBuilder(schema).build(), "/myfirst");
		bean.setName("profileGraphQLBean");
		return bean;
	}
}
