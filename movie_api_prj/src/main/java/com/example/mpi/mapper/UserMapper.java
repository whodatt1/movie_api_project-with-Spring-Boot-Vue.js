package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.UserDto;

@Mapper
public interface UserMapper {
	
	int saveUser(UserDto user);
	
	UserDto findByUserId(String userId);
	
	boolean existsByUserId(String userId);

	int updateUser(UserDto user);

	int updateLastLogin(String userId);

	int deleteUser(UserDto user);

	List<UserDto> getUserList();
	
}
