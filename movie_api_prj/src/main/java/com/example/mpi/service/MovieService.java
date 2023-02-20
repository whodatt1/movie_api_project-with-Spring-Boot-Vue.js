package com.example.mpi.service;

import java.util.List;

import com.example.mpi.entity.Movie;

public interface MovieService {
	// 메인페이지 개봉예정작 리스트
	List<Movie> getMovieForMainUpComing(String adult);
}
