package com.example.mpi.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	int createComment(CommentDto commentDto);

	List<CommentDto> getCommentList(String commId);

	int delComment(CommentDto commentDto);

	void deleteCommentForComm(HashMap<String, Object> hashMap);
}
