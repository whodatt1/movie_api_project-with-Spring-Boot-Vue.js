package com.example.mpi.payload.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	
	@NotEmpty(message = "아이디는 필수 입력 사항입니다.")
	private String userId;
	
	@NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String userPw;
	
	@NotEmpty(message = "닉네임은 필수 입력 사항입니다.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String userNickNm;
	
	@NotEmpty(message = "이메일은 필수 입력 사항입니다.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	private String userEmail;
	
	private boolean adult;
	
}
