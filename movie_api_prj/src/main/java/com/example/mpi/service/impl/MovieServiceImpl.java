package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.dto.MovieDto;
import com.example.mpi.mapper.MovieMapper;
import com.example.mpi.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieMapper movieMapper;
	
	@Override
	public List<MovieDto> getMovieForMainUpComing(String adult) {
		return movieMapper.getMovieForMainUpComing(adult);
	}

	@Override
	public List<MovieDto> getMovieForMainLatest(String adult) {
		return movieMapper.getMovieForMainLatest(adult);
	}

	@Override
	public List<MovieDto> getMovieForMainTop(String adult) {
		return movieMapper.getMovieForMainTop(adult);
	}

	@Override
	public List<MovieDto> getMovieForMainPopular(String adult) {
		return movieMapper.getMovieForMainPopular(adult);
	}

	@Override
	public MovieDto getMovieDetail(String id) {
		return movieMapper.getMovieDetail(id);
	}
	
}
