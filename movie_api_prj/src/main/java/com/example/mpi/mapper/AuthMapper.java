package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.entity.Auth;

@Mapper
public interface AuthMapper {
	
	List<Auth> findByUserId(String userId);
	
	int saveAuth(Auth auth);
	
}
