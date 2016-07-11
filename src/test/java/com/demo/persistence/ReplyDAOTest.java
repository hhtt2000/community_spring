package com.demo.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class ReplyDAOTest {

	@Inject
	private ReplyDAO dao;
	
	@Test
	public void testCreate() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setBno(20);
		vo.setReplyer("anonymous01");
		vo.setReplytext("댓글 내용");
		
		dao.create(vo);
	}
	
	@Test
	public void testUpdate() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setRno(1);
		vo.setReplytext("댓글 수정 내용");
		
		dao.update(vo);
	}
	
	@Test
	public void testList() throws Exception {
		//dao.list(20);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}
	
	@Test
	public void testGetUserid() throws Exception {
		String userid = dao.getUserid(33);
		
		System.out.println("userid: " + userid);
	}
}
