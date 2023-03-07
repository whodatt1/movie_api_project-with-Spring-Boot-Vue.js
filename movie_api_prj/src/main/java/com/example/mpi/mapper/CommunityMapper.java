package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.paging.Criteria;

@Mapper
public interface CommunityMapper {
	int saveCommunity(CommunityDto communityDto);

	List<CommunityDto> getCommunityListAll(Criteria criteria);

	int getCommunityTotalCount(Criteria criteria);

	CommunityDto getCommunityDetail(String id);
}
