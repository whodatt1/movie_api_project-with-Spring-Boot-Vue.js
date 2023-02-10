package com.example.mpi.writer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

public class JdbcBatchGenreCdItemListWriter<T> implements ItemWriter<List<T>> {
	
	
	private final JdbcBatchItemWriter<T> jdbcBatchItemWriter;
	
	public JdbcBatchGenreCdItemListWriter(JdbcBatchItemWriter<T> jdbcBatchItemWriter) {
		this.jdbcBatchItemWriter = jdbcBatchItemWriter;
	}

	@Override
	public void write(List<? extends List<T>> items) throws Exception {
		
		List<T> genres = items.stream().flatMap(Collection::stream).collect(Collectors.toList());
		
		String sql = "insert into tb_movie_genre_cd "
				+ "( "
				+ "	id, "
				+ "	name, "
				+ "	data_reg_at, "
				+ "	data_mod_at  "
				+ ") "
				+ "values  "
				+ "( "
				+ "	:id, "
				+ "	:name, "
				+ "	now(), "
				+ "	now() "
				+ ") "
				+ "on duplicate key update "
				+ "name = :name, "
				+ "data_mod_at = now()";
		
		jdbcBatchItemWriter.setSql(sql);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		jdbcBatchItemWriter.afterPropertiesSet();
		jdbcBatchItemWriter.write(genres);
		
	}
	
}
