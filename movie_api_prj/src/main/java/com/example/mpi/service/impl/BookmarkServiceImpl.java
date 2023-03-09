package com.example.mpi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mpi.dto.BookmarkDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.mapper.BookmarkMapper;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;
import com.example.mpi.service.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	BookmarkMapper bookmarkMapper;

	@Override
	public int createBookmark(BookmarkDto bookmarkDto) {
		return bookmarkMapper.createBookmark(bookmarkDto);
	}

	@Override
	public boolean getIsBookmark(BookmarkDto bookmarkDto) {
		return bookmarkMapper.getIsBookmark(bookmarkDto);
	}

	@Override
	public int deleteBookmark(BookmarkDto bookmarkDto) {
		return bookmarkMapper.deleteBookmark(bookmarkDto);
	}

	@Override
	public PagingHandler getBookmarkListByUserId(Criteria criteria) {
		List<MovieDto> movieList = bookmarkMapper.getBookmarkListByUserId(criteria);
		int totalCount = bookmarkMapper.getBookmarkListByUserIdTotalCount(criteria);
		return new PagingHandler(criteria, totalCount, movieList);
	}
	
}
