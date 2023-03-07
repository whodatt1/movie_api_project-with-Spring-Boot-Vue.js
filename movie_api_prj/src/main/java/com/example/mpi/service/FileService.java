package com.example.mpi.service;

import java.util.List;

import com.example.mpi.dto.FileDto;

public interface FileService {
	
	int saveFile(FileDto fileDto);
	
	List<FileDto> getFileList(String commId);
}
