package com.example.mpi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	
	private long id;
	
	private String title;
	
	private String originalTitle;
	
	private String genreIds;
	
	private String overview;
	
	private float voteAverage;
	
	private String releaseDate;
	
	private String posterPath;
	
	private String backdropPath;
	
	private boolean adult;
	
	private boolean video;
	
	private String originalLanguage;
	
	private String dataRegDt;
	
	private String dataModDt;
	
}
