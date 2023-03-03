package com.example.mpi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
	
	private long id;
	
	private String title;
	
	private String originalTitle;
	
	private String genreIds;
	
	private String overview;
	
	private float popularity;
	
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
