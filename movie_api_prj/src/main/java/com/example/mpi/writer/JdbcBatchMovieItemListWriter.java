package com.example.mpi.writer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

public class JdbcBatchMovieItemListWriter<T> implements ItemWriter<List<T>> {
	
	private final JdbcBatchItemWriter<T> jdbcBatchItemWriter;
	
	public JdbcBatchMovieItemListWriter(JdbcBatchItemWriter<T> jdbcBatchItemWriter) {
		this.jdbcBatchItemWriter = jdbcBatchItemWriter;
	}
	
	@Override
	public void write(List<? extends List<T>> items) throws Exception {
		
		List<T> movies = items.stream().flatMap(Collection::stream).collect(Collectors.toList());
		
		String sql = "INSERT INTO tb_movie "
				+ "( "
				+ "	id, "
				+ "	title,  "
				+ "	original_title, "
				+ "	genre_ids, "
				+ "	overview, "
				+ " popularity, "
				+ "	vote_average, "
				+ "	release_date, "
				+ "	poster_path, "
				+ "	backdrop_path, "
				+ "	adult, "
				+ "	video, "
				+ "	original_language, "
				+ "	data_reg_dt, "
				+ "	data_mod_dt "
				+ ") "
				+ "VALUES  "
				+ "( "
				+ "	:id, "
				+ "	:title, "
				+ "	:originalTitle, "
				+ "	:genreIds, "
				+ "	:overview, "
				+ " :popularity, "
				+ "	:voteAverage, "
				+ "	:releaseDate, "
				+ "	:posterPath, "
				+ "	:backdropPath, "
				+ "	:adult, "
				+ "	:video, "
				+ "	:originalLanguage, "
				+ "	now(), "
				+ "	now() "
				+ ")  "
				+ "ON DUPLICATE KEY UPDATE "
				+ "title = :title, "
				+ "original_title = :originalTitle, "
				+ "genre_ids = :genreIds, "
				+ "overview = :overview, "
				+ "popularity = :popularity, "
				+ "vote_average = :voteAverage, "
				+ "release_date = :releaseDate, "
				+ "poster_path = :posterPath, "
				+ "backdrop_path = :backdropPath, "
				+ "adult = :adult, "
				+ "video = :video, "
				+ "original_language = :originalLanguage, "
				+ "data_mod_dt = now()";
		
		jdbcBatchItemWriter.setSql(sql);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		jdbcBatchItemWriter.afterPropertiesSet();
		jdbcBatchItemWriter.write(movies);
	}
	
}
