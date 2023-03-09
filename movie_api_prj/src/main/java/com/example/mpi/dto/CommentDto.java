package com.example.mpi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
	public long id;
	public String writerId;
	public String content;
	public long vote;
	public boolean del;
	public String regDt;
	public String regUserId;
	public String modDt;
	public String modUserId;
	public long commId;
	
}
