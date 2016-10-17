package com.billshare.services.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billshare.constants.ResponseCode;
import com.billshare.constants.Status;
import com.billshare.constants.UserMessages;
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

	@Override
	public ResponseStatus login(User user) {
		ResponseStatus responseStatus = new ResponseStatus();
		User login = userDao.login(user);
		if (login != null) {
			responseStatus.setUser(login);
			responseStatus.setCode(ResponseCode.SUCCESS);
			responseStatus.setStatus(Status.SUCCESS);

		} else {
			responseStatus.setCode(ResponseCode.AUTH_INVALID);
			responseStatus.setMessage(UserMessages.INVALID_CREDENTILAS);
		}
		return responseStatus;
	}

}
