package com.neb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {
	
	@Autowired
	ApplicationContext context;
	
	@RequestMapping("/beans")
	@GetMapping
	public Map<String, String> getBeans(){
		
		String [] beans= context.getBeanDefinitionNames();
		
		Map<String, String> beanMap= new HashMap<>();
		
		for(String bean : beans ) {
			beanMap.put(bean, context.getBean(bean).toString());
		}
		return beanMap;
	}

}
