package com.example.mpi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mpi.dto.BookmarkDto;
import com.example.mpi.dto.MovieDto;
import com.example.mpi.paging.Criteria;

@Mapper
public interface BookmarkMapper {

	int createBookmark(BookmarkDto bookmarkDto);

	boolean getIsBookmark(BookmarkDto bookmarkDto);

	int deleteBookmark(BookmarkDto bookmarkDto);

	List<MovieDto> getBookmarkListByUserId(Criteria criteria);

	int getBookmarkListByUserIdTotalCount(Criteria criteria);

}
