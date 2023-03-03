package com.example.mpi.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.GenreCdDto;

@Mapper
public interface GenreCdMapper {
	
	List<GenreCdDto> getGenreCdList(HashMap<String, Object> paramMap);

	List<GenreCdDto> getGenreCdListAll();
}
