package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.mpi.entity.User;
import com.example.mpi.repository.UserRepository;
import com.example.mpi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Map<String, String> validateHandling(BindingResult bindingResult) {
		
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : bindingResult.getFieldErrors()) {
			validatorResult.put(error.getField(), error.getDefaultMessage());
		}
		
		return validatorResult;
	}

	@Override
	public int saveUser(User user) {
		return userRepository.saveUser(user);
	}

}
