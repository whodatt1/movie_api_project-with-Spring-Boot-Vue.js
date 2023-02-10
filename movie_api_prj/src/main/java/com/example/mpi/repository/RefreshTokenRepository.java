package com.example.mpi.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.entity.RefreshToken;
import com.example.mpi.entity.User;

@Mapper
public interface RefreshTokenRepository {
	Optional<RefreshToken> findByToken(String token);
	RefreshToken findById(String id);
	String save(RefreshToken refreshToken);
	int deleteByUser(User user);
	int deleteByToken(RefreshToken refreshToken);
}
