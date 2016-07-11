package com.demo.dto;

public class LoginDTO {

	private String userid;
	private String userpw;
	private boolean useCookie;
	
	public String getUserid() {
		return userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", userpw=" + userpw + ", useCookie=" + useCookie + "]";
	}
	
}
