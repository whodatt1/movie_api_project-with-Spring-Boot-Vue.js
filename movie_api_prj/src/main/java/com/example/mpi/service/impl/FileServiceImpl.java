package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.dto.FileDto;
import com.example.mpi.mapper.FileMapper;
import com.example.mpi.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileMapper fileMapper;

	@Override
	public int saveFile(FileDto fileDto) {
		return fileMapper.saveFile(fileDto);
	}

	@Override
	public List<FileDto> getFileList(String commId) {
		return fileMapper.getFileList(commId);
	}

}
