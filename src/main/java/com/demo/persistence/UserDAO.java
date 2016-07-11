package com.demo.persistence;

import java.util.Date;

import com.demo.domain.UserVO;
import com.demo.dto.LoginDTO;

public interface UserDAO {

	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String userid, String sessionId, Date next) throws Exception;
	
	public UserVO checkUserWithSessionKey(String value) throws Exception;
	
	public void updateUpointAndRole(String userid, Integer upoint, String role) throws Exception;
	
	public UserVO getUserLevelInfo(String userid) throws Exception;
	
	public void create(UserVO vo) throws Exception;
	
}
