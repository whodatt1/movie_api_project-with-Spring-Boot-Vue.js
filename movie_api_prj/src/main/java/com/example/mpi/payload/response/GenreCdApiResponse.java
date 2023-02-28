package com.example.mpi.payload.response;

import java.util.List;

import com.example.mpi.dto.GenreCdDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenreCdApiResponse {
	
	private long id;
	private String name;
	
	public GenreCdDto toEntity() {
		
		return GenreCdDto.builder()
				.id(id)
				.name(name)
				.build();
	}
	
	// MOVIE API에서 받아온 영화 장르 리스트 정보
	@Setter
	@Getter
	public static class Genre {
		private long id;
		private String name;
	}
	
	@Data
	public static class ResponseMovieGenreApi {
		
		private List<Genre> genres;
		
		public boolean requestSuccess() {
			
			if (genres != null && genres.size() != 0) {
				return true;
			}
			
			return false;
		}
	}
}
