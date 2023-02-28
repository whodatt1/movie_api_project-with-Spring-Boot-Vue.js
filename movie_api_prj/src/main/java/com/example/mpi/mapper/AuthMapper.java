package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.AuthDto;

@Mapper
public interface AuthMapper {
	
	List<AuthDto> findByUserId(String userId);
	
	int saveAuth(AuthDto auth);
	
}
