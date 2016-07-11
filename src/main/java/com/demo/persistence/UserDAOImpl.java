package com.demo.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.domain.UserVO;
import com.demo.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.demo.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void keepLogin(String userid, String sessionId, Date next) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("sessionId", sessionId);
		map.put("next", next);
		
		session.update(namespace + ".keepLogin", map);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) throws Exception {
		return session.selectOne(namespace + ".checkUserWithSessionKey", value);
	}

	@Override
	public void updateUpointAndRole(String userid, Integer point, String role) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("upoint", point);
		map.put("role", role);
		
		session.update(namespace + ".updateUpoint", map);
	}

	@Override
	public UserVO getUserLevelInfo(String userid) throws Exception {
		return session.selectOne(namespace + ".getUserLevelInfo", userid);
	}

	@Override
	public void create(UserVO vo) throws Exception {
		session.insert(namespace + ".register", vo);
	}

}
