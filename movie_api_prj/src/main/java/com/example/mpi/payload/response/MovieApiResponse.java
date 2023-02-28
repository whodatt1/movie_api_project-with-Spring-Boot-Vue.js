package com.example.mpi.payload.response;

import java.util.Arrays;
import java.util.List;

import com.example.mpi.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieApiResponse {
	
	private boolean adult;
	private Integer[] genreIds;
	private long id;
	private String originalLanguage;
	private String originalTitle;
	private String overview;
	private float popularity;
	private String posterPath;
	private String backdropPath;
	private String releaseDate;
	private String title;
	private boolean video;
	private float voteAverage;
	private long voteCount;
	
	public MovieDto toEntity() {
		
		// 배열을 comma로 분리하여 String column 값으로 전달
		String genreIdsWithComma = Arrays.stream(genreIds)
				.map(String::valueOf)
				.reduce((x, y) -> x + "," + y).orElse("");
		
		// 개봉일이 없는 경우가 있어 예외 처리
		if ("".equals(releaseDate)) {
			releaseDate = null;
		}
		
		return MovieDto.builder()
				.id(id)
				.title(title)
				.originalTitle(originalTitle)
				.genreIds(genreIdsWithComma)
				.overview(overview)
				.popularity(popularity)
				.voteAverage(voteAverage)
				.releaseDate(releaseDate)
				.posterPath(posterPath)
				.backdropPath(backdropPath)
				.adult(adult)
				.originalLanguage(originalLanguage)
				.video(video)
				.build();
	}

	// MOVIE API에서 받아온 영화 리스트 정보
	@Getter
	@Setter
	public static class Result {
		private boolean adult;
		@JsonProperty("backdrop_path")
		private String backdropPath;
		@JsonProperty("genre_ids")
		private String[] genreIds;
		private long id;
		@JsonProperty("original_language")
		private String originalLanguage;
		@JsonProperty("original_title")
		private String originalTitle;
		private String overview;
		private float popularity;
		@JsonProperty("poster_path")
		private String posterPath;
		@JsonProperty("release_date")
		private String releaseDate;
		private String title;
		private boolean video;
		@JsonProperty("vote_average")
		private float voteAverage;
		@JsonProperty("vote_count")
		private long voteCount;
	}
	
	
	@Data
	public static class ResponseMovieApi {
		
		private int page;
		private List<Result> results;
		@JsonProperty("total_pages")
		private int totalPages;
		@JsonProperty("total_results")
		private int totalResults;
		
		public boolean requestSuccess() {
			// results 컬렉션이 null이거나 size()가 0이면 TRUE 반환
			if (results != null && results.size() != 0) {
				return true;
			}
			
			return false;
		}
		
		public boolean isLastPage() {
			// API 확인 결과 501 페이지 요청할 시 페이지는 500보다 작거나 같아야 한다는 에러 문구로 인해 500으로 하드코딩
			if (!requestSuccess() && getPage() > 500) {
				return true;
			}
			
			return false;
		}
	}
}
