package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.entity.Auth;
import com.example.mpi.mapper.AuthMapper;
import com.example.mpi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthMapper authMapper;

	@Override
	public int saveAuth(List<Auth> auths) {
		int result = 0;
		
		for (Auth auth : auths) {
			result += authMapper.saveAuth(auth);
		}
		
		return result;
	}

}
