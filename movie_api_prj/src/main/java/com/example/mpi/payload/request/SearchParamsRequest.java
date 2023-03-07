package com.example.mpi.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchParamsRequest {
	
	private int pageNo;
	private String type;
	private String keyWord;
	
}
