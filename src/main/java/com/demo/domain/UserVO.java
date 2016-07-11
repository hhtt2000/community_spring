package com.demo.domain;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserVO {

	@Pattern(regexp="^[A-Za-z][A-Za-z\\d]+$", message="알파벳으로 시작하여 알파벳과 숫자만 사용할 수 있습니다.")
	@Size(min=3, max=50, message="아이디는 3자 이상이어야 합니다.")
	private String userid;
	
	@Pattern(regexp="^[\\w`~!@#$%^&*()-+=]+$", message="알파벳, 숫자, 특수문자(`~!@#$%^&*()_-+=)를 사용할 수 있습니다.")
	@Size(min=6, max=50, message="패스워드는 6자 이상이어야 합니다.")
	private String userpw;
	
	@Pattern(regexp="^[가-힣A-Za-z0-9]+$", message="한글, 영어, 숫자를 사용할 수 있습니다.")
	@Size(min=2, max=50, message="이름은 2자 이상이어야 합니다.")
	private String username;
	
	@Email(message="이메일 형식이 아닙니다.")
	private String email;
	
	private Date regdate;
	private Date updatedate;
	private int upoint;
	private String role;
	
	public String getUserid() {
		return userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public int getUpoint() {
		return upoint;
	}
	public String getRole() {
		return role;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + ", upoint=" + upoint + ", role=" + role + "]";
	}
	
}
