package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	int createReview(ReviewDto reviewDto);

	List<ReviewDto> getReviewList(String movieId);

	int delReview(ReviewDto reviewDto);

	float getRatingsAverage(String movieId);

}
