package com.billshare.services.impl;

import java.util.List;

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
import com.billshare.utils.ImageUtils;
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
		user.set_profilePic(ImageUtils.instance().getBlobFromString(userDTO.getProfilePic()));
		// user.setProfilePic(null);
		ResponseStatus responseStatus = new ResponseStatus();
		if (userDao.register(user)) {
			UserDTO map = dozerBeanMapper.map(user, UserDTO.class);
			map.setProfilePic(ImageUtils.instance().getByteStringFromBlob(user.get_profilePic()));
			responseStatus.setUser(map);
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
		UserDTO login = userDao.login(user);
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

	@Override
	public ResponseStatus list(String id) {
		ResponseStatus responseStatus = new ResponseStatus();
		List<UserDTO> users = userDao.getUserList(id);
		if (users != null) {
			responseStatus.setUsers(users);
			responseStatus.setCode(ResponseCode.SUCCESS);
			responseStatus.setStatus(Status.SUCCESS);

		} else {
			responseStatus.setCode(ResponseCode.AUTH_INVALID);
			responseStatus.setMessage(UserMessages.INVALID_CREDENTILAS);
		}
		return responseStatus;
	}

}
