package com.example.mpi.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsBookmarkParamRequest {
	
	private long movieId;
	private String userId;
}
