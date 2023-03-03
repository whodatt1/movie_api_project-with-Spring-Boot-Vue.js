package com.example.mpi.service;

import java.util.HashMap;
import java.util.List;

import com.example.mpi.dto.GenreCdDto;

public interface GenreCdService {
	
	List<GenreCdDto> getGenreCdList(HashMap<String, Object> paramMap);

	List<GenreCdDto> getGenreCdListAll();
}
