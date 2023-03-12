package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.mapper.CommentMapper;
import com.example.mpi.mapper.CommunityMapper;
import com.example.mpi.mapper.FileMapper;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.payload.request.CommRequest;
import com.example.mpi.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityMapper communityMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Override
	public int saveCommunity(CommunityDto communityDto) {
		return communityMapper.saveCommunity(communityDto);
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
	public PagingHandler getCommunityListAll(Criteria criteria) {
		List<CommunityDto> communityList = communityMapper.getCommunityListAll(criteria);
		int totalCount = communityMapper.getCommunityTotalCount(criteria);
		return new PagingHandler(criteria, communityList, totalCount);
	}
	
	@Transactional
	@Override
	public CommunityDto getCommunityDetail(String id) {
		communityMapper.updateViews(id);
		return communityMapper.getCommunityDetail(id);
	}
	
	@Transactional
	@Override
	public int delCommunity(CommunityDto communityDto) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("commId", communityDto.getId());
		hashMap.put("modUserId", communityDto.getModUserId());
		hashMap.put("del", 1);
		
		fileMapper.deleteFileForComm(hashMap);
		commentMapper.deleteCommentForComm(hashMap);
		return communityMapper.delCommunity(communityDto);
	}

	@Override
	public CommunityDto getCommunityDetailForUpdate(String id) {
		return communityMapper.getCommunityDetail(id);
	}

	@Override
	public int delImgForUpdate(String uuid) {
		return fileMapper.delImgForUpdate(uuid);
	}

	@Override
	public int updateCommunity(CommunityDto communityDto) {
		return communityMapper.updateCommunity(communityDto);
	}

	@Override
	public int voteCommunity(String id) {
		return communityMapper.voteCommunity(id);
	}

	@Override
	public List<CommunityDto> getCommunityListAllNotice() {
		return communityMapper.getCommunityListAllNotice();
	}

}
