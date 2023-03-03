package com.example.mpi.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	private int pageNo;
	private int qty;
	
	private String genre;
	private String sortBy;
	private String title;
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getForMoreBtnQty() {
		return this.pageNo * qty;
	}
	
	public int getPageStart() {
		return (this.pageNo - 1) * qty;
	}
}
