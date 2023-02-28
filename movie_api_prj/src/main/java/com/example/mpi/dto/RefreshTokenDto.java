package com.example.mpi.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenDto {
	
	private long id;
	
	private String userId;
	
	private String token;
	
	private Instant expiryDate;
	
}
