package com.example.mpi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreCd {
	
	private long id;
	
	private String name;
	
	private String dataRegAt;
	
	private String dataModAt;
	
}
