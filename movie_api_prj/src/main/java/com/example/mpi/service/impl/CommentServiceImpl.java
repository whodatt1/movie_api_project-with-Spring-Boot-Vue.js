package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mpi.dto.CommentDto;
import com.example.mpi.mapper.CommentMapper;
import com.example.mpi.mapper.CommunityMapper;
import com.example.mpi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
			validatorResult.put(error.getField(), error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	@Transactional
	@Override
	public int createComment(CommentDto commentDto) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("commId", commentDto.getCommId());
		hashMap.put("cnt", 1);
		communityMapper.updateReplyCnt(hashMap);
		return commentMapper.createComment(commentDto);
	}

	@Override
	public List<CommentDto> getCommentList(String commId) {
		return commentMapper.getCommentList(commId);
	}
	
	@Transactional
	@Override
	public int delComment(CommentDto commentDto) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("commId", commentDto.getCommId());
		hashMap.put("cnt", -1);
		communityMapper.updateReplyCnt(hashMap);
		return commentMapper.delComment(commentDto);
	}
	
	
}
