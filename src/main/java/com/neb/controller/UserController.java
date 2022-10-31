package com.neb.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neb.dto.MyResponse;
import com.neb.dto.UserRequest;
import com.neb.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@PostMapping
	public ResponseEntity<MyResponse> register(@Valid @RequestBody UserRequest userRequest){
		userService.saveUser(userRequest);
		 MyResponse response=new MyResponse("Successfully Registered", true);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
