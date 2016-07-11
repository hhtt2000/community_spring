package com.demo.service;

import java.util.Date;

import com.demo.domain.UserVO;
import com.demo.dto.LoginDTO;

public interface UserService {

	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String userid, String sessionId, Date next) throws Exception;
	
	public UserVO checkLoginBefore(String value) throws Exception;
	
	public void updateUpointAndRole(String userid, Integer point) throws Exception;
	
	public UserVO register(UserVO vo) throws Exception;
	
}
