package com.springboot.crudoperations.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.crudoperations.MyInterceptor;

@Component
public class InterceptoprConfig implements WebMvcConfigurer {

	@Autowired
	private MyInterceptor myInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(myInterceptor);
	}
	
	

}
