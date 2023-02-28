package com.example.mpi.service;

import java.util.List;

import com.example.mpi.dto.AuthDto;

public interface AuthService {
	
	int saveAuth(List<AuthDto> auths);
}
