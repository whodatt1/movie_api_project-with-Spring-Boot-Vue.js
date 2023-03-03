package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.MovieDto;
import com.example.mpi.paging.Criteria;

@Mapper
public interface MovieMapper {
	
	List<MovieDto> getMovieForMainUpComing(String adult);
	
	List<MovieDto> getMovieForMainLatest(String adult);
	
	List<MovieDto> getMovieForMainTop(String adult);
	
	List<MovieDto> getMovieForMainPopular(String adult);
	
	MovieDto getMovieDetail(String id);

	List<MovieDto> getMovieListAll(Criteria criteria);

	int getMovieTotalCount(Criteria criteria);
}
