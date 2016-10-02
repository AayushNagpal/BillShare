package com.billshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.billshare.dto.UserDTO;
import com.billshare.services.UserService;
import com.billshare.utils.ResponseStatus;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseStatus register(UserDTO userDTO) {
		return userService.register(userDTO);
	}

}
