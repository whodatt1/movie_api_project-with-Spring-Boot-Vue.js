package com.example.mpi.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;

public interface CommunityService {
	
	int saveCommunity(CommunityDto communityDto);
	
	Map<String, String> validateHandling(Errors erros);

	PagingHandler getCommunityListAll(Criteria criteria);

	CommunityDto getCommunityDetail(String id);
}
