package com.billshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billshare.dto.GroupDTO;
import com.billshare.services.GroupService;
import com.billshare.utils.GroupsList;

@RestController
@RequestMapping("/groups")
public class GroupController {
	@Autowired
	GroupService groupService;

	@RequestMapping("/save")
	public GroupDTO saveGroup(@RequestBody GroupDTO dto) {

		return groupService.saveGroup(dto);
	}

	@RequestMapping("/list")
	public GroupsList getGroups(@RequestParam("id") String id) {

		return groupService.getList(id);
	}
}
