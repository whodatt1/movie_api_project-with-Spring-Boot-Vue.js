package com.example.mpi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_movie")
public class Movie {
	
	@Id
	private long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 100)
	private String originalTitle;
	
	@Column(length = 100)
	private String genreIds;
	
	@Column
	private String overview;
	
	@Column
	private float voteAverage;
	
	@Column
	private String releaseDate;
	
	@Column(length = 256)
	private String posterPath;
	
	@Column(length = 256)
	private String backdropPath;
	
	@Column
	private boolean adult;
	
	@Column
	private boolean video;
	
	@Column
	private String originalLanguage;
	
	@Column
	private String dataRegDt;
	
	@Column
	private String dataModDt;
}
