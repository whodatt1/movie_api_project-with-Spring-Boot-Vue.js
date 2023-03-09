package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkDto {
	
	private long id;
	private long movieId;
	private String userId;
	private boolean del;
	private String regAt;
	private String modAt;
}
