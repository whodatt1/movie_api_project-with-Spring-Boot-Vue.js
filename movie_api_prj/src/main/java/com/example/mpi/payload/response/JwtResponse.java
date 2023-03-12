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
	
	private String userEmail;
	
	private boolean adult;
	
	private String regDt;
	
	private List<String> roles;

	public JwtResponse(String accessToken, String userId, String userNickNm, String userEmail, boolean adult, String regDt, List<String> roles) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.userNickNm = userNickNm;
		this.userEmail = userEmail;
		this.adult = adult;
		this.regDt = regDt;
		this.roles = roles;
	}
	
}
