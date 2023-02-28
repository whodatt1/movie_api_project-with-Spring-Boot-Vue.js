package com.example.mpi.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.mpi.dto.GenreCdDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.payload.response.GenreCdApiResponse;
import com.example.mpi.payload.response.MovieApiResponse;
import com.example.mpi.processor.CustomGenreCdItemProcessor;
import com.example.mpi.processor.CustomMovieItemProcessor;
import com.example.mpi.reader.CustomGenreCdItemReader;
import com.example.mpi.reader.CustomMovieItemReader;
import com.example.mpi.writer.JdbcBatchGenreCdItemListWriter;
import com.example.mpi.writer.JdbcBatchMovieItemListWriter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MovieApiJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;
	
	// 영화 정보 가져오는 Job
	@Bean
	public Job movieApiJob() {
		
		Job movieApiJob = jobBuilderFactory.get("movieApiJob")
				.start(movieApiStep())
				.build();
		
		return movieApiJob;
	}
	
	@Bean
	public Job genreCdApiJob() {
		
		Job movieGenreApiJob = jobBuilderFactory.get("movieGenreApiJob")
				.start(genreCdApiStep())
				.build();
		
		return movieGenreApiJob;
	}
	
	@Bean
	public Step movieApiStep() {
		return stepBuilderFactory.get("openApiStep")
				.<List<MovieApiResponse>, List<MovieDto>>chunk(1)
				.reader(movieItemReader())
				.processor(movieItemProcessor())
				.writer(jdbcBatchMovieItemListWriter())
				.build();
	}
	
	@Bean
	public Step genreCdApiStep() {
		return stepBuilderFactory.get("openApiStep")
				.<List<GenreCdApiResponse>, List<GenreCdDto>>chunk(1)
				.reader(genreCdItemReader())
				.processor(genreCdItemProcessor())
				.writer(jdbcBatchGenreCdItemListWriter())
				.build();
	}

	@Bean
	public ItemReader<List<MovieApiResponse>> movieItemReader() {
		return new CustomMovieItemReader();
	}
	
	@Bean
	public ItemReader<List<GenreCdApiResponse>> genreCdItemReader() {
		return new CustomGenreCdItemReader();
	}
	
	@Bean
	public ItemProcessor<List<MovieApiResponse>, List<MovieDto>> movieItemProcessor() {
		return new CustomMovieItemProcessor();
	}
	
	@Bean
	public ItemProcessor<List<GenreCdApiResponse>, List<GenreCdDto>> genreCdItemProcessor() {
		return new CustomGenreCdItemProcessor();
	}
	
	public JdbcBatchMovieItemListWriter<MovieDto> jdbcBatchMovieItemListWriter() {
		JdbcBatchItemWriter<MovieDto> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
		return new JdbcBatchMovieItemListWriter<>(writer);
	}
	
	public JdbcBatchGenreCdItemListWriter<GenreCdDto> jdbcBatchGenreCdItemListWriter() {
		JdbcBatchItemWriter<GenreCdDto> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
		return new JdbcBatchGenreCdItemListWriter<>(writer);
	}
}
