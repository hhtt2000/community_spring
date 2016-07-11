package com.demo.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class UserServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Inject
	private UserService service;
	
	@Test
	public void testRegisterUser() throws Exception {
		UserVO vo = new UserVO();
		vo.setUserid("user03");
		vo.setUserpw("user03");
		vo.setUsername("USER03");
		vo.setEmail("user03@abc.com");
		
		service.register(vo);
	}
}
