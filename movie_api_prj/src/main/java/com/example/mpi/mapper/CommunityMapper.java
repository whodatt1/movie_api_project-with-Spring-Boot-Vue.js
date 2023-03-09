package com.example.mpi.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.payload.request.CommRequest;

@Mapper
public interface CommunityMapper {
	int saveCommunity(CommunityDto communityDto);

	List<CommunityDto> getCommunityListAll(Criteria criteria);

	int getCommunityTotalCount(Criteria criteria);

	CommunityDto getCommunityDetail(String id);

	void updateReplyCnt(HashMap<String, Object> hashMap);

	void updateViews(String id);

	int delCommunity(CommunityDto communityDto);

	int updateCommunity(CommunityDto communityDto);

	int voteCommunity(String id);

}
