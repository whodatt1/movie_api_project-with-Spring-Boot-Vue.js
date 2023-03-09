package com.example.mpi.payload.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentRequest {
	
	// create
	private String writerId;
	@NotEmpty(message = "내용은 필수 입력 사항입니다.")
	private String content;
	private long commId;
	
	// del
	private long id;
	private String userId;
}
