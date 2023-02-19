package com.example.mpi.entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
	
	private long id;
	
	private String userId;
	
	private String token;
	
	private Instant expiryDate;
	
}
