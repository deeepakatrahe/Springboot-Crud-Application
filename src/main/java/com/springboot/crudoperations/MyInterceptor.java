package com.springboot.crudoperations;

import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor{

	private static final Logger log = LogManager.getLogger(MyInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			Date date1 = new Date();
			
			System.out.println(date1);
			
			Date date2 = new Date(Long.parseLong("1220227200"));
			
			System.out.println(date2);
			
			
			
		
		log.info("preHandle called");
		
		String auth = request.getHeader("authorization");
		log.info("authorization " + auth);
		byte[] decodedBytes = Base64.getDecoder().decode(auth);
		String decodedString = new String(decodedBytes);
		
		String arr[] = decodedString.split("~");
		
		byte[] decodedBytesTime = Base64.getDecoder().decode(arr[0]);
		String timeStampBase64 = new String(decodedBytesTime);
		
		log.info("timeStampBase64 "+ timeStampBase64);
		
		byte[] decodedBytesStringOne = Base64.getDecoder().decode(arr[1]);
		String stringOneBase64 = new String(decodedBytesStringOne);
		
		log.info("stringOneBase64" + stringOneBase64);
		
		byte[] decodedBytesStringTwo = Base64.getDecoder().decode(arr[2]);
		String stringTwoBase64 = new String(decodedBytesStringTwo);
		
		log.info("stringTwoBase64 " + stringTwoBase64);
		
		Date date = new Date(Long.parseLong(timeStampBase64));
		if((new Date().getSeconds() - date.getSeconds()>50)){
			return false;
		}
		
		if(!("Hello".equals(stringOneBase64))) {
			return false;
		}
		
		if(!("Hello".equals(stringTwoBase64))) {
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("post Handler" + request.getRequestURI(), request.getMethod());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		if(ex!=null) {
			log.error("exception inside afterCompletion " +ex.getMessage());
		}
		log.info("after Handler" + request.getRequestURI(), request.getMethod());
	}

	
	
	
}
