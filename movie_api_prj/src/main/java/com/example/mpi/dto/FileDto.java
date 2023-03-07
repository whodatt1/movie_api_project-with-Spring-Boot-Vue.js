package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
	
	private String uuid;
	private long commId;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long fileSize;
	private String regAt;
	
}
