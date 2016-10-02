package com.billshare.services.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.constants.ResponseCode;
import com.billshare.constants.Status;
import com.billshare.dao.UserDao;
import com.billshare.domain.User;
import com.billshare.dto.UserDTO;
import com.billshare.services.UserService;
import com.billshare.utils.ResponseStatus;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Override
	public ResponseStatus register(UserDTO userDTO) {
		User user = dozerBeanMapper.map(userDTO, User.class);
		ResponseStatus responseStatus = new ResponseStatus();
		if (userDao.register(user)) {
			responseStatus.setUser(user);
			responseStatus.setCode(ResponseCode.SUCCESS);
			responseStatus.setStatus(Status.SUCCESS);
			return responseStatus;
		}
		responseStatus.setCode(ResponseCode.FAILURE);
		return responseStatus;
	}

}
