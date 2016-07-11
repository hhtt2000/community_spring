package com.demo.service;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.domain.UserVO;
import com.demo.dto.LoginDTO;
import com.demo.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String userid, String sessionId, Date next) throws Exception {
		dao.keepLogin(userid, sessionId, next);
	}

	@Override
	public UserVO checkLoginBefore(String value) throws Exception {
		return dao.checkUserWithSessionKey(value);
	}

	/**
	 * role 결정 계산 방법 = user의 upoint / 100 + 1
	 *  ex) 120 / 100 + 1 = 레벨 2 
	 */
	@Transactional
	@Override
	public void updateUpointAndRole(String userid, Integer point) throws Exception {
		UserVO userLevelInfo = dao.getUserLevelInfo(userid);
		
		if(userLevelInfo != null) {
			if(userLevelInfo.getRole().equals("admin")) {
				return;
			}
			
			int resultPoint = userLevelInfo.getUpoint() + point;
			int level = resultPoint / 100 + 1;
			
			String role = "user_" + level;
			
			dao.updateUpointAndRole(userid, point, role);
			
			// 로그인 세션 업데이트
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			
			UserVO loginInfo = (UserVO) session.getAttribute("login");
			// 업데이트하는 아이디와 현재 세션 아이디가 같을 경우 세션 업데이트
			// 관리자가 일반 사용자 글 삭제하는 경우 PASS
			if(userid.equals(loginInfo.getUserid())) {
				LoginDTO dto = new LoginDTO();
				dto.setUserid(loginInfo.getUserid());
				dto.setUserpw(loginInfo.getUserpw());
				
				session.setAttribute("login", dao.login(dto));
			}
		}
	}

	@Transactional
	@Override
	public UserVO register(UserVO vo) throws Exception {
		vo.setRole("user_1");
		
		dao.create(vo);
		
		LoginDTO loginInfo = new LoginDTO();
		loginInfo.setUserid(vo.getUserid());
		loginInfo.setUserpw(vo.getUserpw());
		
		return login(loginInfo);
	}

}
