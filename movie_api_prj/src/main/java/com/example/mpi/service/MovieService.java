package com.example.mpi.service;

import java.util.List;

import com.example.mpi.entity.Movie;

public interface MovieService {
	// 메인페이지 개봉예정작 리스트
	List<Movie> getMovieForMainUpComing(String adult);
	// 메인페이지 개봉작 최신 영화 리스트
	List<Movie> getMovieForMainLatest(String adult);
	// 메인페이지 평점순 영화 리스트
	List<Movie> getMovieForMainTop(String adult);
	// 메인페이지 인기순 영화 리스트
	List<Movie> getMovieForMainPopular(String adult);
}
