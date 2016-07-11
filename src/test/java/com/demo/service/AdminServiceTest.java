package com.demo.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class AdminServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);
	
	@Inject
	private AdminService service;
	
	@Test
	public void testDeleteBoardByAdmin() throws Exception {
		service.deleteBoardByAdmin(462831);
	}
	
	@Test
	public void testDeleteReplyByAdmin() throws Exception {
		service.deleteReplyByAdmin(42);
	}
}
