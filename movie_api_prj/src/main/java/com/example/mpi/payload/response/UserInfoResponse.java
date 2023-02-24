package com.example.mpi.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
	private String userId;
	private String userNickNm;
	private boolean adult;
	private List<String> roles;
	
	public UserInfoResponse(String userId, String userNickNm, boolean adult, List<String> roles) {
		this.userId = userId;
		this.userNickNm = userNickNm;
		this.adult = adult;
		this.roles = roles;
	}
}
