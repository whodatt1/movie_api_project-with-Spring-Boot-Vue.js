package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mpi.dto.ReviewDto;
import com.example.mpi.mapper.ReviewMapper;
import com.example.mpi.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public int createReivew(ReviewDto reviewDto) {
		return reviewMapper.createReview(reviewDto);
	}

	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
			validatorResult.put(error.getField(), error.getDefaultMessage());
		}
		
		return validatorResult;
	}

	@Override
	public List<ReviewDto> getReviewList(String movieId) {
		return reviewMapper.getReviewList(movieId);
	}

	@Override
	public int delReview(ReviewDto reviewDto) {
		return reviewMapper.delReview(reviewDto);
	}

	@Override
	public float getRatingsAverage(String movieId) {
		return reviewMapper.getRatingsAverage(movieId);
	}

}
