package com.example.mpi.reader;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.mpi.payload.response.GenreCdApiResponse;
import com.example.mpi.payload.response.MovieApiResponse;

import reactor.core.publisher.Mono;

public class CustomGenreCdItemReader implements ItemReader<List<GenreCdApiResponse>> {

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${movie.openApi.genre.path}")
	private String moviePath;
	
	@Value("${movie.openApi.host}")
	private String movieHost;
	
	@Value("${movie.openApi.apiKey}")
	private String apiKey;
	
	private int cnt = 0;
	
	@Override
	public List<GenreCdApiResponse> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		GenreCdApiResponse.ResponseMovieGenreApi genre = getMovieGenreList();
		
		// 호출이 실패한 경우는 break
		if (!genre.requestSuccess()) {
			throw new Exception("");
		}
		
		cnt++;
		
		// API 확인해보니 페이지넘버가 따로없어 한번 받은 후 NULL 값 반환하여 read() 멈춤
		return cnt == 1 ? genre.getGenres().stream().map(genreInfo -> {
			GenreCdApiResponse genreDto = modelMapper.map(genreInfo, GenreCdApiResponse.class);
			
			return genreDto;	
		}).collect(Collectors.toList()) : null;
	}
	
	public GenreCdApiResponse.ResponseMovieGenreApi getMovieGenreList() throws Exception {
		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder.scheme("https")
						.host(movieHost)
						.path(moviePath)
						.queryParam("api_key", apiKey)
						.queryParam("language", "ko-KR")
						.build())
				.retrieve()
				.onStatus(HttpStatus::isError, clientResponse -> Mono.error(new Exception()))
				.bodyToMono(GenreCdApiResponse.ResponseMovieGenreApi.class)
				.flux()
				.toStream()
				.findFirst().orElse(null);
	}

}
