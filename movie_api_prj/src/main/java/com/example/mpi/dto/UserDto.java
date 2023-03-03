package com.example.mpi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	
	private String userId;
	
	private String userPw;
	
	private String userNickNm;
	
	private String userEmail;
	
	private List<AuthDto> authList;
	
	private long userPoint;
	
	private String profileImgPath;
	
	private boolean adult;
	
	private boolean del;
	
	private String regDt;
	
	private String regUserId;
	
	private String modDt;
	
	private String modUserId;
	
}
