package com.example.mpi.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.mpi.entity.User;

@Mapper
public interface UserMapper {
	
	int saveUser(User user);
	
	User findByUserId(String userId);
	
	boolean existsByUserId(String userId);
	
}
