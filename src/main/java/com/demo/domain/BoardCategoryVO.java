package com.demo.domain;

import java.io.Serializable;

public class BoardCategoryVO implements Serializable {


	private static final long serialVersionUID = -7468594555890269301L;
	
	private String boardType;
	private String boardName;
	private String role;
	
	public String getBoardType() {
		return boardType;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "BoardCategoryVO [boardType=" + boardType + ", boardName=" + boardName + ", role=" + role + "]";
	}
	
}
