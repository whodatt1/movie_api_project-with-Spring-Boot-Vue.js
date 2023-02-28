package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.dto.AuthDto;
import com.example.mpi.mapper.AuthMapper;
import com.example.mpi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthMapper authMapper;

	@Override
	public int saveAuth(List<AuthDto> auths) {
		int result = 0;
		
		for (AuthDto auth : auths) {
			result += authMapper.saveAuth(auth);
		}
		
		return result;
	}

}
