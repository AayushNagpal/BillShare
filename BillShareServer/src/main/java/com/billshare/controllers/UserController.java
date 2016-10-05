package com.billshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billshare.dto.UserDTO;
import com.billshare.services.UserService;
import com.billshare.utils.ResponseStatus;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/register")
	public ResponseStatus register(@RequestBody UserDTO userDTO) {
		return userService.register(userDTO);
	}

}
