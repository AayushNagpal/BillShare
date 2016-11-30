package com.billshare.controllers;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping("/saveGroup")
	public GroupDTO saveGroup(/*@RequestPart("group_info") GroupDTO dto,*/ MultipartFile file) {

		return groupService.saveGroup(null);
	}
	@RequestMapping("/list")
	public GroupsList getGroups(@RequestParam("id") String id) {

		return groupService.getList(id);
	}
	@RequestMapping("/update")
	public GroupDTO update(@RequestBody GroupDTO dto) {

		return groupService.update(dto);
	}
}
