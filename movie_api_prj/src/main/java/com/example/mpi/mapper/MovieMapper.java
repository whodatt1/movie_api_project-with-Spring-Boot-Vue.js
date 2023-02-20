package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.entity.Movie;

@Mapper
public interface MovieMapper {
	
	List<Movie> getMovieForMainUpComing(String adult);
}
