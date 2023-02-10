package com.example.mpi.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import com.example.mpi.dto.MovieDto;
import com.example.mpi.entity.Movie;

public class CustomMovieItemProcessor implements ItemProcessor<List<MovieDto>, List<Movie>> {

	@Override
	public List<Movie> process(List<MovieDto> item) throws Exception {
		// MovieDto to Movie
		return item.stream().map(MovieDto::toEntity).collect(Collectors.toList());
	}

}
