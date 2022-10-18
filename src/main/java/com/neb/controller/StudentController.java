package com.neb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neb.domain.Student;
import com.neb.service.StudentService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
	
	@Autowired
	private StudentService stdService;
	
	@GetMapping("/welcome")
	public String welcome(HttpServletRequest servletRequest) {
		
		return "Welcome to the Student Controller via " + servletRequest.getServletPath();
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){
		stdService.saveStudent(student);
		Map<String, String> message=new HashMap<>();
		message.put("message", "Student is created successfully");
		message.put("succes", "true");
		
		return new ResponseEntity<>(message,HttpStatus.CREATED);
		
	}

}
