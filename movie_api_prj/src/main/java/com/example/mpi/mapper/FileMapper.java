package com.example.mpi.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.FileDto;

@Mapper
public interface FileMapper {
	
	int saveFile(FileDto fileDto);

	List<FileDto> getFileList(String commId);

	void deleteFileForComm(HashMap<String, Object> hashMap);

	int delImgForUpdate(String uuid);
	
}
