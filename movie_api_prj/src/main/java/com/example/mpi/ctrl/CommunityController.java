package com.example.mpi.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mpi.dto.FileDto;
import com.example.mpi.dto.handler.FileHandler;
import com.example.mpi.payload.request.CommCreateRequest;

import io.netty.channel.unix.Errors;

@RestController
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	private FileHandler fileHandler;
	
	@PostMapping("create")
	public ResponseEntity<?> createCommunity(@Valid @RequestBody CommCreateRequest commCreateRequest, Errors errors, @RequestParam(name = "files", required = false) MultipartFile[] files) {
		
		List<FileDto> fileList = null;
		
		if (files[0].getSize() > 0) {
			fileList = fileHandler.uploadFiles(files);
			commCreateRequest.setFile(true);
		} else {
			commCreateRequest.setFile(false);
		}
		
		return ResponseEntity.ok().body(null);
	}
}
