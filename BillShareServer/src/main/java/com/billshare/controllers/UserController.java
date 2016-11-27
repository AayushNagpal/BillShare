package com.billshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billshare.domain.User;
import com.billshare.dto.UserDTO;
import com.billshare.services.UserService;
import com.billshare.utils.ResponseStatus;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/register")
	public ResponseStatus register(@RequestBody UserDTO userDTO) {
		return userService.register(userDTO);
	}
	@RequestMapping("/login")
	public ResponseStatus login(@RequestBody User user) {
		return userService.login(user);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseStatus list(@RequestParam("id") String id) {
		return userService.list(id);
	}
}
