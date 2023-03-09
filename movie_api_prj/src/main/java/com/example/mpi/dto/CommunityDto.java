package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommunityDto {
	
	private long id;
	private String category;
	private String title;
	private String writerId;
	private String content;
	private boolean del;
	private boolean file;
	private long views;
	private long vote;
	private String regDt;
	private String regUserId;
	private String modDt;
	private String modUserId;
	private long replyCount;
	
}
