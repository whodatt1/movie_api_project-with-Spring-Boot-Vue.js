package com.example.mpi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	int createReview(ReviewDto reviewDto);

	List<ReviewDto> getReviewList(String movieId);

	int delReview(ReviewDto reviewDto);

	Object getRatingsAverage(String movieId);

	Map<String, Object> getReviewData(String userId);

}
