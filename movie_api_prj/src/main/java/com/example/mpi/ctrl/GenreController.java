package com.example.mpi.ctrl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.GenreCdDto;
import com.example.mpi.service.GenreCdService;

@RestController
@RequestMapping("/genre")
public class GenreController {
	
	@Autowired
	GenreCdService genreCdService;
	
	@GetMapping("/cd/{genreIds}")
	public ResponseEntity<List<GenreCdDto>> getGenreCdList(@PathVariable("genreIds") String genreIds) {
		
		try {
			String[] genreIdArr = genreIds.split(",");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("genreIdArr", genreIdArr);
			
			List<GenreCdDto> genreCdList = genreCdService.getGenreCdList(paramMap);
			
			if (genreCdList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(genreCdList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cd")
	public ResponseEntity<List<GenreCdDto>> getGenreCdListAll() {
		
		try {
			List<GenreCdDto> genreCdList = genreCdService.getGenreCdListAll();
			
			if (genreCdList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(genreCdList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
