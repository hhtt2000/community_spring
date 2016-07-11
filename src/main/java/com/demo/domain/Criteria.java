package com.demo.domain;

public class Criteria {
	
	private int page;
	private int perPageNum;
	private String boardType;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 50) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}

	public String getBoardType() {
		return boardType;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
