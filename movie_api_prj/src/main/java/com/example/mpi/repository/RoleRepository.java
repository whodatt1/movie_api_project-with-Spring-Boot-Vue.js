package com.example.mpi.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.entity.Role;

@Mapper
public interface RoleRepository {
	
	int saveRole(Role role);
}
