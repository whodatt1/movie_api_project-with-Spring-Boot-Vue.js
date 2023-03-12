package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mpi.dto.UserDto;
import com.example.mpi.mapper.UserMapper;
import com.example.mpi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public Map<String, String> validateHandling(Errors errors) {
		
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
			validatorResult.put(error.getField(), error.getDefaultMessage());
		}
		
		return validatorResult;
	}

	@Override
	public int saveUser(UserDto user) {
		return userMapper.saveUser(user);
	}

	@Override
	public boolean existsByUserId(String userId) {
		return userMapper.existsByUserId(userId);
	}

	@Override
	public int updateUser(UserDto user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int updateLastLogin(String userId) {
		return userMapper.updateLastLogin(userId);
	}

	@Override
	public int deleteUser(UserDto user) {
		return userMapper.deleteUser(user);
	}

	@Override
	public List<UserDto> getUserList() {
		return userMapper.getUserList();
	}

}
