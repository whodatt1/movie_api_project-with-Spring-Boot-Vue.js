package com.example.mpi.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.BookmarkDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.payload.request.BookmarkRequest;
import com.example.mpi.payload.request.IsBookmarkParamRequest;
import com.example.mpi.service.BookmarkService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
	
	@Autowired
	BookmarkService bookmarkService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createComment(@RequestBody BookmarkRequest bookmarkRequest) {
		
		BookmarkDto bookmarkDto = BookmarkDto.builder()
											.movieId(bookmarkRequest.getMovieId())
											.userId(bookmarkRequest.getUserId())
											.build();
		
		int result = bookmarkService.createBookmark(bookmarkDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteComment(@RequestBody BookmarkRequest bookmarkRequest) {
		
		BookmarkDto bookmarkDto = BookmarkDto.builder()
				.movieId(bookmarkRequest.getMovieId())
				.userId(bookmarkRequest.getUserId())
				.del(true)
				.build();
		
		int result = bookmarkService.deleteBookmark(bookmarkDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/is")
	public ResponseEntity<?> getIsBookmark(IsBookmarkParamRequest isBookmarkParamRequest) {
		
		try {
			BookmarkDto bookmarkDto = BookmarkDto.builder()
					.movieId(isBookmarkParamRequest.getMovieId())
					.userId(isBookmarkParamRequest.getUserId())
					.build();
			
			boolean result = bookmarkService.getIsBookmark(bookmarkDto);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/list/{userId}/{pageNo}")
	public ResponseEntity<?> getBookmarkListByUserId(@PathVariable("userId") String userId, @PathVariable("pageNo") int pageNo) {
		
		Criteria criteria = new Criteria(pageNo, 20);
		criteria.setUserId(userId);
		PagingHandler movieList = bookmarkService.getBookmarkListByUserId(criteria);
		
		if (movieList.getMovieList().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}
}
