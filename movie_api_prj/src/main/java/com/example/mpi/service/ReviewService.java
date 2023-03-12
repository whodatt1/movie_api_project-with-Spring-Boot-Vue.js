package com.example.mpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.example.mpi.dto.ReviewDto;

public interface ReviewService {
	
	int createReivew(ReviewDto reviewDto);

	Map<String, String> validateHandling(Errors errors);

	List<ReviewDto> getReviewList(String movieId);

	int delReview(ReviewDto reviewDto);

	Object getRatingsAverage(String movieId);

	Map<String, Object> getReviewData(String userId);
}
