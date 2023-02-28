package com.example.mpi.service;

import java.util.List;

import com.example.mpi.dto.MovieDto;

public interface MovieService {
	// 메인페이지 개봉예정작 리스트
	List<MovieDto> getMovieForMainUpComing(String adult);
	// 메인페이지 개봉작 최신 영화 리스트
	List<MovieDto> getMovieForMainLatest(String adult);
	// 메인페이지 평점순 영화 리스트
	List<MovieDto> getMovieForMainTop(String adult);
	// 메인페이지 인기순 영화 리스트
	List<MovieDto> getMovieForMainPopular(String adult);
	// 영화 디테일
	MovieDto getMovieDetail(String id);
}
