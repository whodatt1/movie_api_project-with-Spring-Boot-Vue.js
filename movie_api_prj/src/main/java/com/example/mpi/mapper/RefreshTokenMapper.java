package com.example.mpi.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.entity.RefreshToken;
import com.example.mpi.entity.User;

@Mapper
public interface RefreshTokenMapper {
	
	Optional<RefreshToken> findByToken(String token);
	
	RefreshToken findById(String id);
	
	int save(RefreshToken refreshToken);
	
	int deleteByUser(User user);
	
	int deleteByToken(RefreshToken refreshToken);
	
}
