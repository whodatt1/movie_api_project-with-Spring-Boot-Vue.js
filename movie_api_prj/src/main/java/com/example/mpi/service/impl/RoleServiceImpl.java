package com.example.mpi.service.impl;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.entity.Role;
import com.example.mpi.repository.RoleRepository;
import com.example.mpi.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public int saveRole(List<Role> roles) {
		
		int result = 0;
		
		for (Role role : roles) {
			result += roleRepository.saveRole(role);
		}
		
		return result;
	}

}
