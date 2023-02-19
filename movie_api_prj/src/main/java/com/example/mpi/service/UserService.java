package com.example.mpi.service;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.example.mpi.entity.User;

public interface UserService {
	/* 유저 저장 */
	int saveUser(User user);
	/* 가입 시 밸리데이션 */
	Map<String, String> validateHandling(Errors errors);
	/* 아이디 중복체크 */
	boolean existsByUserId(String userId);
}
