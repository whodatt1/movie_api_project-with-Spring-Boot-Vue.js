package com.example.mpi.payload.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommCreateRequest {
	
	@NotEmpty(message = "종류는 필수 입력 사항입니다.")
	private String category;
	
	@NotEmpty(message = "제목은 필수 입력 사항입니다.")
	private String title;
	
	private String writerId;
	
	@NotEmpty(message = "내용은 필수 입력 사항입니다.")
	private String content;
	
	private boolean file;
}
