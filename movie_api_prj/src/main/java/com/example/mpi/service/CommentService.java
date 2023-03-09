package com.example.mpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.example.mpi.dto.CommentDto;

public interface CommentService {
	
	int createComment(CommentDto commentDto);
	
	Map<String, String> validateHandling(Errors erros);
	
	List<CommentDto> getCommentList(String commId);

	int delComment(CommentDto commentDto);
}
