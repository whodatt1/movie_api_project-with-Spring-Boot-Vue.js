package com.example.mpi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
	
	private String userId;
	
	private String auth;
	
}
