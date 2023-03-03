package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.dto.GenreCdDto;
import com.example.mpi.mapper.GenreCdMapper;
import com.example.mpi.service.GenreCdService;

@Service
public class GenreCdServiceImpl implements GenreCdService {
	
	@Autowired
	GenreCdMapper genreCdMapper;

	@Override
	public List<GenreCdDto> getGenreCdList(HashMap<String, Object> paramMap) {
		return genreCdMapper.getGenreCdList(paramMap);
	}

	@Override
	public List<GenreCdDto> getGenreCdListAll() {
		return genreCdMapper.getGenreCdListAll();
	}

}
