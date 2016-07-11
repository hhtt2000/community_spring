package com.demo.dto;

public class ClaimDTO {

	private Integer cno;
	private String status;
	
	public Integer getCno() {
		return cno;
	}
	public String getStatus() {
		return status;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ClaimDTO [cno=" + cno + ", status=" + status + "]";
	}
	
}
