package com.example.mpi.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.RefreshTokenDto;
import com.example.mpi.dto.UserDto;

@Mapper
public interface RefreshTokenMapper {
	
	Optional<RefreshTokenDto> findByToken(String token);
	
	RefreshTokenDto findById(String id);
	
	int save(RefreshTokenDto refreshToken);
	
	int deleteByUser(UserDto user);
	
	int deleteByToken(RefreshTokenDto refreshToken);
	
}
