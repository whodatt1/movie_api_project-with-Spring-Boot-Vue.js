package com.example.mpi.service;

import java.util.List;

import com.example.mpi.entity.Auth;

public interface AuthService {
	
	int saveAuth(List<Auth> auths);
}
