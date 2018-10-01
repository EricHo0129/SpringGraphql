package com.eric.graphql.config;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.SchemaParser;
import com.eric.graphql.manager.GreetingMutationManager;
import com.eric.graphql.manager.GreetingQueryManager;
import com.eric.graphql.manager.ProfileQueryManager;

import graphql.schema.GraphQLSchema;import graphql.servlet.GraphQLServletListener;
import graphql.servlet.SimpleGraphQLHttpServlet;

@Configuration
public class ServletConfig {
	
	Logger log = LoggerFactory.getLogger(ServletConfig.class);
	
	public GraphQLServletListener graphQLServletListener() {
		return new GraphQLServletListener() {
			@Override
			public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
				return new RequestCallback() {
					@Override
					public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
						log.info("Oops! something wrong..."+throwable.getMessage());
					}
				};
			}
		};
	}
	
	@Bean
	public ServletRegistrationBean<Servlet> greetingGraphQLBean(GreetingQueryManager greetingQueryManager, GreetingMutationManager greetingMutationManager) {
		GraphQLSchema schema = SchemaParser.newParser().files("root.graphqls","greeting.graphqls").
				resolvers(greetingQueryManager,greetingMutationManager).
				build().makeExecutableSchema();
		SimpleGraphQLHttpServlet servlet = SimpleGraphQLHttpServlet.newBuilder(schema).build();
		servlet.addListener(graphQLServletListener());
		ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<Servlet>(servlet, "/mysecond");
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
