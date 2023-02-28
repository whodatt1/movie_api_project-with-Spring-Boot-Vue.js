package com.example.mpi.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import com.example.mpi.dto.GenreCdDto;
import com.example.mpi.payload.response.GenreCdApiResponse;
import com.example.mpi.payload.response.MovieApiResponse;

public class CustomGenreCdItemProcessor implements ItemProcessor<List<GenreCdApiResponse>, List<GenreCdDto>> {

	@Override
	public List<GenreCdDto> process(List<GenreCdApiResponse> item) throws Exception {
		// GenreDto to Genre
		return item.stream().map(GenreCdApiResponse::toEntity).collect(Collectors.toList());
	}

}
