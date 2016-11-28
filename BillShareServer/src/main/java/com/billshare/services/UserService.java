package com.billshare.services;

import com.billshare.domain.User;
import com.billshare.dto.UserDTO;
import com.billshare.utils.ResponseStatus;

public interface UserService {
public ResponseStatus register(UserDTO userDTO);
public ResponseStatus login(User user);
public ResponseStatus list(String id);
}
