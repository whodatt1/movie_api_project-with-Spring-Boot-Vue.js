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
	
}
