package com.example.mpi.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import com.example.mpi.dto.MovieDto;
import com.example.mpi.payload.response.MovieApiResponse;

public class CustomMovieItemProcessor implements ItemProcessor<List<MovieApiResponse>, List<MovieDto>> {

	@Override
	public List<MovieDto> process(List<MovieApiResponse> item) throws Exception {
		// MovieDto to Movie
		return item.stream().map(MovieApiResponse::toEntity).collect(Collectors.toList());
	}

}
