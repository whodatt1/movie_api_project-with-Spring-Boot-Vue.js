package com.example.mpi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.mapper.CommunityMapper;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityMapper communityMapper;
	
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

	@Override
	public CommunityDto getCommunityDetail(String id) {
		return communityMapper.getCommunityDetail(id);
	}

}
