package com.example.mpi.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private String userId;
	
	private String userPw;
	
	private String userNickNm;
	
	private String userEmail;
	
	private List<Auth> authList;
	
	private long userPoint;
	
	private String profileImgPath;
	
	private boolean adult;
	
	private boolean del;
	
	private String regDt;
	
	private String regUserId;
	
	private String modDt;
	
	private String modUserId;
	
}
