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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.dto.FileDto;
import com.example.mpi.dto.handler.FileHandler;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.payload.request.CommCreateRequest;
import com.example.mpi.payload.request.SearchParamsRequest;
import com.example.mpi.service.CommunityService;
import com.example.mpi.service.FileService;


@RestController
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private CommunityService communityService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCommunity(@Valid @RequestPart CommCreateRequest commCreateRequest, Errors errors, @RequestParam(name = "files", required = false) MultipartFile[] files) {
		
		try {
			if (errors.hasErrors()) {
				Map<String, String> validatorResult = communityService.validateHandling(errors);
				return ResponseEntity.badRequest().body(validatorResult);
			}
			
			List<FileDto> fileList = null;
			
			if (files != null) {
				if (files[0].getSize() > 0) {
					fileList = fileHandler.uploadFiles(files);
				}
			}
			
			CommunityDto communityDto = CommunityDto.builder()
					.category(commCreateRequest.getCategory())
					.title(commCreateRequest.getTitle())
					.writerId(commCreateRequest.getWriterId())
					.content(commCreateRequest.getContent())
					.file(commCreateRequest.isFile())
					.build();
			
			communityService.saveCommunity(communityDto);
			
			if (communityDto.getId() != 0) {
				if (fileList != null) {
					for (FileDto fileDto : fileList) {
						fileDto.setCommId(communityDto.getId());
						fileService.saveFile(fileDto);
					}
				}
			}
			
			return new ResponseEntity<>(communityDto.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/pub/list")
	public ResponseEntity<PagingHandler> getCommunityAll(SearchParamsRequest searchParamsRequest) {
		
		Criteria criteria = new Criteria(searchParamsRequest.getPageNo(), 10);
		criteria.setType(searchParamsRequest.getType());
		criteria.setKeyWord(searchParamsRequest.getKeyWord());
		System.out.println(criteria.toString());
		
		PagingHandler communityListAll = communityService.getCommunityListAll(criteria);
		
		if (communityListAll.getCommunityList().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(communityListAll, HttpStatus.OK);
	}
	
	@GetMapping("/pub/detail/{id}")
	public ResponseEntity<CommunityDto> getCommunityDetail(@PathVariable("id") String id) {
		
		CommunityDto communityDetail = communityService.getCommunityDetail(id);
		
		if (communityDetail == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(communityDetail, HttpStatus.OK);
	}
	
	@GetMapping("/pub/detail/img/{commId}")
	public ResponseEntity<List<FileDto>> getCommunityDetailImg(@PathVariable("commId") String commId) {
		
		List<FileDto> fileList = fileService.getFileList(commId);
		
		if (fileList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}
}
