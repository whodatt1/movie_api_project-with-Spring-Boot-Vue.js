package com.example.mpi.ctrl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.CommentDto;
import com.example.mpi.dto.ReviewDto;
import com.example.mpi.payload.request.CommentRequest;
import com.example.mpi.payload.request.ReviewRequest;
import com.example.mpi.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest reviewRequest, Errors errors) {
		
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = reviewService.validateHandling(errors);
			return ResponseEntity.badRequest().body(validatorResult);
		}
		
		ReviewDto reviewDto = ReviewDto.builder()
								.writerId(reviewRequest.getWriterId())
								.content(reviewRequest.getContent())
								.ratings(reviewRequest.getRatings())
								.movieId(reviewRequest.getMovieId())
								.build();
		
		int result = reviewService.createReivew(reviewDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/pub/list/{movieId}")
	public ResponseEntity<List<ReviewDto>> getReviewList(@PathVariable("movieId") String movieId) {
		
		List<ReviewDto> reviewList = reviewService.getReviewList(movieId);
		
		if (reviewList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(reviewList, HttpStatus.OK);
	}
	
	@GetMapping("/pub/average/{movieId}")
	public ResponseEntity<?> getRatingsAverage(@PathVariable("movieId") String movieId) {
		
		try {
			float average = reviewService.getRatingsAverage(movieId);
			
			return new ResponseEntity<>(average, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PostMapping("/del")
	public ResponseEntity<?> delReview(@RequestBody ReviewRequest reviewRequest) {
		ReviewDto reviewDto = ReviewDto.builder()
										.id(reviewRequest.getId())
										.del(true)
										.modUserId(reviewRequest.getUserId())
										.movieId(reviewRequest.getMovieId())
										.build();
		
		int result = reviewService.delReview(reviewDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
