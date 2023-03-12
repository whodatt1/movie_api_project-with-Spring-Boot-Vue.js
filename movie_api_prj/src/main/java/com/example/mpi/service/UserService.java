package com.example.mpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.example.mpi.dto.UserDto;

public interface UserService {
	/* 유저 저장 */
	int saveUser(UserDto user);
	/* 가입 시 밸리데이션 */
	Map<String, String> validateHandling(Errors errors);
	/* 아이디 중복체크 */
	boolean existsByUserId(String userId);
	/* 계정정보 수정 */
	int updateUser(UserDto user);
	/* 마지막 접속정보 수정 */
	int updateLastLogin(String userId);
	/* 유저정보 삭제 */
	int deleteUser(UserDto user);
	/* 유저 정보 가져오기 */
	List<UserDto> getUserList();
}
