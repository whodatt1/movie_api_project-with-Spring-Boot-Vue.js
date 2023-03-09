package com.example.mpi.service;

import com.example.mpi.dto.BookmarkDto;
import com.example.mpi.paging.Criteria;
import com.example.mpi.paging.handler.PagingHandler;

public interface BookmarkService {
	
	int createBookmark(BookmarkDto bookmarkDto);

	boolean getIsBookmark(BookmarkDto bookmarkDto);

	int deleteBookmark(BookmarkDto bookmarkDto);

	PagingHandler getBookmarkListByUserId(Criteria criteria);
}
