package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.entity.Movie;
import com.example.mpi.mapper.MovieMapper;
import com.example.mpi.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieMapper movieMapper;
	
	@Override
	public List<Movie> getMovieForMainUpComing(String adult) {
		return movieMapper.getMovieForMainUpComing(adult);
	}

	@Override
	public List<Movie> getMovieForMainLatest(String adult) {
		return movieMapper.getMovieForMainLatest(adult);
	}

	@Override
	public List<Movie> getMovieForMainTop(String adult) {
		return movieMapper.getMovieForMainTop(adult);
	}

	@Override
	public List<Movie> getMovieForMainPopular(String adult) {
		return movieMapper.getMovieForMainPopular(adult);
	}
	
}
