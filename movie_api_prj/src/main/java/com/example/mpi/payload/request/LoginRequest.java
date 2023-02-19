package com.example.mpi.payload.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	
	@NotEmpty(message = "아이디는 필수 입력 사항입니다.")
	private String userId;
	
	@NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String userPw;
	
}
