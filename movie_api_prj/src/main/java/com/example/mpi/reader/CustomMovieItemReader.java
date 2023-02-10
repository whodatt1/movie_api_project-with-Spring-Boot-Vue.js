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

import com.example.mpi.dto.MovieDto;

import reactor.core.publisher.Mono;

public class CustomMovieItemReader implements ItemReader<List<MovieDto>> {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${movie.openApi.discover.path}")
	private String moviePath;
	
	@Value("${movie.openApi.host}")
	private String movieHost;
	
	@Value("${movie.openApi.apiKey}")
	private String apiKey;
	
	// 현재 페이지
	private int currPage = 1;
	
	@Override
	public List<MovieDto> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		MovieDto.ResponseMovieApi result = getMovieList(currPage);
		
		// 호출이 실패한 경우는 break
		if (!result.requestSuccess()) {
			throw new Exception("");
		}
		
		if (!result.isLastPage()) {
			currPage++;
		} else {
			// 마지막 페이지인 경우 null을 반환하여 read 종료 (null을 반환하지 않을 경우 read는 반복 수행)
			return null;
		}
		
		return result.getResults().stream().map(movieInfo -> {
			MovieDto movieDto = modelMapper.map(movieInfo, MovieDto.class);
			
			return movieDto;
		}).collect(Collectors.toList());
	}
	
	public MovieDto.ResponseMovieApi getMovieList(int currPage) throws Exception {
		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder.scheme("https")
						.host(movieHost)
						.path(moviePath)
						.queryParam("api_key", apiKey)
						.queryParam("watch_region", "KR")
						.queryParam("language", "ko-KR")
						.queryParam("page", currPage)
						.build())
				.retrieve()
				.onStatus(HttpStatus::isError, clientResponse -> Mono.error(new Exception()))
				.bodyToMono(MovieDto.ResponseMovieApi.class)
				.flux()
				.toStream()
				.findFirst().orElse(null);
	}
}
