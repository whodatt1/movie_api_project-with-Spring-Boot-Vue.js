package com.example.mpi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreCdDto {
	
	private long id;
	
	private String name;
	
	private String dataRegAt;
	
	private String dataModAt;
	
}
