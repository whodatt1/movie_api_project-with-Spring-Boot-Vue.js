package com.example.mpi.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	
	private String token;
	
	private String type = "Bearer";
	
	private String refreshToken;
	
	private String userId;
	
	private String userNickNm;
	
	private String userEmail;
	
	private long userPoint;
	
	private String profileImgPath;
	
	private boolean adult;
	
	private List<String> roles;

	public JwtResponse(String token, String refreshToken, String userId, String userNickNm, String userEmail,
			long userPoint, String profileImgPath, boolean adult, List<String> roles) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.userId = userId;
		this.userNickNm = userNickNm;
		this.userEmail = userEmail;
		this.userPoint = userPoint;
		this.profileImgPath = profileImgPath;
		this.adult = adult;
		this.roles = roles;
	}
	
}
