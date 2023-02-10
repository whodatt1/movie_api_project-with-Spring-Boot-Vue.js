package com.example.mpi.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.mpi.entity.User;

@Mapper
@Component
public interface UserRepository {
	Optional<User> findByUserId(String userId);
}
