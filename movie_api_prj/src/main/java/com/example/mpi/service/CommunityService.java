package com.example.mpi.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.payload.request.CommRequest;

public interface CommunityService {
	
	int saveCommunity(CommunityDto communityDto);
	
	Map<String, String> validateHandling(Errors erros);

	PagingHandler getCommunityListAll(Criteria criteria);

	CommunityDto getCommunityDetail(String id);

	int delCommunity(CommunityDto communityDto);

	CommunityDto getCommunityDetailForUpdate(String id);

	int delImgForUpdate(String uuid);

	int updateCommunity(CommunityDto communityDto);

	int voteCommunity(String id);
}
