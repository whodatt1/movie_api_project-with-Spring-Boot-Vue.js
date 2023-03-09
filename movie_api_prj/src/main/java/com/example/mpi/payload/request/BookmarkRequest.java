package com.example.mpi.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookmarkRequest {
	
	private long movieId;
	private String userId;
}
