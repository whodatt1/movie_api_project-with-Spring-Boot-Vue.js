package com.example.mpi.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.example.mpi.entity.User;

public interface UserService {
	int saveUser(User user);
	Map<String, String> validateHandling(BindingResult bindingResult);
}
