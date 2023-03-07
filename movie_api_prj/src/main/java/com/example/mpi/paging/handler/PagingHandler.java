package com.example.mpi.paging.handler;

import java.util.List;

import com.example.mpi.dto.CommunityDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.paging.Criteria;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private Criteria criteria;
	
	private List<MovieDto> movieList;
	private List<CommunityDto> communityList;
	
	public PagingHandler(Criteria criteria, int totalCount) {
		this.criteria = criteria;
		this.totalCount = totalCount;
		
		this.endPage = (int) (Math.ceil(criteria.getPageNo() / (criteria.getQty() * 1.0))) * criteria.getQty();
		this.startPage = this.endPage - 9;
		
		int realEndPage = (int) (Math.ceil((totalCount * 1.0) / criteria.getQty()));
		
		if (realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
	public PagingHandler(Criteria criteria, int totalCount, List<MovieDto> list) {
		this(criteria, totalCount);
		this.movieList = list;
	}
	
	public PagingHandler(Criteria criteria, List<CommunityDto> list, int totalCount) {
		this(criteria, totalCount);
		this.communityList = list;
	}
}
