package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
	
	private long id;
	private String writerId;
	private float ratings;
	private String content;
	private boolean del;
	private String regDt;
	private String regUserId;
	private String modDt;
	private String modUserId;
	private long movieId;
}
