package com.example.mpi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	
	public TokenRefreshResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
