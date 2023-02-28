package com.example.mpi.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.mpi.dto.UserDto;

@Mapper
public interface UserMapper {
	
	int saveUser(UserDto user);
	
	UserDto findByUserId(String userId);
	
	boolean existsByUserId(String userId);
	
}
