package com.example.mpi.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

import com.example.mpi.dto.GenreCdDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.entity.GenreCd;

public class CustomGenreCdItemProcessor implements ItemProcessor<List<GenreCdDto>, List<GenreCd>> {

	@Override
	public List<GenreCd> process(List<GenreCdDto> item) throws Exception {
		// GenreDto to Genre
		return item.stream().map(GenreCdDto::toEntity).collect(Collectors.toList());
	}

}
