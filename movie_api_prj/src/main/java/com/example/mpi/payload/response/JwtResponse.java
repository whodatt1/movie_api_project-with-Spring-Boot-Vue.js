package com.example.mpi.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	
	private String accessToken;
	
	private String type = "Bearer";
	
	private String userId;
	
	private String userNickNm;
	
	private boolean adult;
	
	private List<String> roles;

	public JwtResponse(String accessToken, String userId, String userNickNm, boolean adult, List<String> roles) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.userNickNm = userNickNm;
		this.adult = adult;
		this.roles = roles;
	}
	
}
