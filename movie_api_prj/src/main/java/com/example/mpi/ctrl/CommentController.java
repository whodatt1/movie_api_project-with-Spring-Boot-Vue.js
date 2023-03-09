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
import com.example.mpi.payload.request.CommentRequest;
import com.example.mpi.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequest commentRequest, Errors errors) {
		
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = commentService.validateHandling(errors);
			return ResponseEntity.badRequest().body(validatorResult);
		}
		
		CommentDto commentDto = CommentDto.builder()
						.commId(commentRequest.getCommId())
						.writerId(commentRequest.getWriterId())
						.content(commentRequest.getContent())
						.build();
		
		int result = commentService.createComment(commentDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/pub/list/{commId}")
	public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable("commId") String commId) {
		
		List<CommentDto> commentList = commentService.getCommentList(commId);
		
		if (commentList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(commentList, HttpStatus.OK);
	}
	
	@PostMapping("/del")
	public ResponseEntity<?> getCommentList(@RequestBody CommentRequest commentRequest) {
		CommentDto commentDto = CommentDto.builder()
										.id(commentRequest.getId())
										.del(true)
										.commId(commentRequest.getCommId())
										.modUserId(commentRequest.getUserId())
										.build();
		
		int result = commentService.delComment(commentDto);
		
		if (result > 0) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
