package com.shail.h2integration.service;

import com.shail.h2integration.dto.UserVO;

public interface IUserService {
	
	boolean exists(long id);
	UserVO create(UserVO userVO);
	UserVO update(UserVO userVO);
	UserVO get(long id);
	void delete(long id);

}
