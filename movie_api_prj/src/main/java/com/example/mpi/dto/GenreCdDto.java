package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreCdDto {
	
	private long id;
	
	private String name;
	
	private String dataRegAt;
	
	private String dataModAt;
	
}
