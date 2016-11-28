package com.billshare.services.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.constants.ResponseCode;
import com.billshare.constants.Status;
import com.billshare.dao.GroupDao;
import com.billshare.domain.Groups;
import com.billshare.dto.GroupDTO;
import com.billshare.services.GroupService;
import com.billshare.utils.GroupsList;
import com.billshare.utils.ResponseStatus;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupDao groupDao;
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Override
	public GroupDTO saveGroup(GroupDTO groupDTO) {
		Groups group = dozerBeanMapper.map(groupDTO, Groups.class);
		ResponseStatus responseStatus = new ResponseStatus();
		if (groupDao.saveGroup(groupDTO, group)) {
			responseStatus.setCode(ResponseCode.SUCCESS);
			responseStatus.setStatus(Status.SUCCESS);
			groupDTO.setStatus(responseStatus);
			return groupDTO;
		}
		responseStatus.setCode(ResponseCode.FAILURE);
		groupDTO.setStatus(responseStatus);
		return groupDTO;
	}

	@Override
	public GroupsList getList(String id) {
		GroupsList groupsList = new GroupsList();
		groupsList.setGroupInfo(groupDao.getList(id));
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(ResponseCode.SUCCESS);
		responseStatus.setStatus(Status.SUCCESS);
		groupsList.setResponseStatus(responseStatus);
		return groupsList;
	}

}
