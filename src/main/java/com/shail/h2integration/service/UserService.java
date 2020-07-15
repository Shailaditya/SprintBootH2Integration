package com.shail.h2integration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shail.h2integration.dto.UserVO;
import com.shail.h2integration.entity.User;
import com.shail.h2integration.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean exists(long id) {
		return userRepository.existsById(id);
	}
	
	@Override
	public UserVO create(UserVO userVO) {
		return this.createOrUpdate(userVO);
	}

	@Override
	public UserVO update(UserVO userVO) {
		return this.createOrUpdate(userVO);
	}

	@Override
	public UserVO get(long id) {
		Optional<User> opt=userRepository.findById(id);
		return opt.isPresent()?convertEntityToVO(opt.get()): new UserVO();
		
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}
	
	private UserVO createOrUpdate(UserVO userVO) {
		User user=convertVOToEntity(userVO);
		user=userRepository.save(user);
		return convertEntityToVO(user);
	}
	
	private UserVO convertEntityToVO(User user) {
		UserVO userVO=new UserVO();
		userVO.setId(user.getId());
		userVO.setAge(user.getAge());
		userVO.setEmailId(user.getEmailId());
		userVO.setName(user.getName());
		return userVO;
	}
	
	private User convertVOToEntity(UserVO userVO) {
		User user=new User();
		user.setId(userVO.getId());
		user.setAge(userVO.getAge());
		user.setEmailId(userVO.getEmailId());
		user.setName(userVO.getName());
		return user;
	}

}
