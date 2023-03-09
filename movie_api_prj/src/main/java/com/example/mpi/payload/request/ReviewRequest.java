package com.example.mpi.payload.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewRequest {
	
	private String writerId;
	@NotEmpty(message = "내용은 필수 입력 사항입니다.")
	private String content;
	@DecimalMin(value = "1", message = "별점은 필수 입력 사항입니다.")
	private float ratings;
	private long movieId;
	
	private String userId;
	private long id;
}
