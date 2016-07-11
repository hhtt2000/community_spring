package com.demo.domain;

import java.util.Date;

public class ClaimVO {

	private Integer cno;
	private String claimer;
	private String title;
	private String content;
	private Date regdate;
	private Date updatedate;
	private String status; // 처리상태(registered, read, completed)
	private Integer bno;
	private Integer rno;
	private String url;
	
	public Integer getCno() {
		return cno;
	}
	public String getClaimer() {
		return claimer;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public void setClaimer(String claimer) {
		this.claimer = claimer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBno() {
		return bno;
	}
	public Integer getRno() {
		return rno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ClaimVO [cno=" + cno + ", claimer=" + claimer + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + ", status=" + status + ", bno=" + bno
				+ ", rno=" + rno + ", url=" + url + "]";
	}
	
}
