package com.demo.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyServiceTest {

	@Inject
	private ReplyService service;
	
	@Test
	public void testAddReply() throws Exception {
		ReplyVO vo = new ReplyVO();
		vo.setBno(462832);
		vo.setReplyer("user00");
		vo.setReplytext("댓글 테스트");
		
		service.addReply(vo, vo.getReplyer());
	}
	
	@Test
	public void testRemoveReply() throws Exception {
		service.removeReply(43, "user00");
	}
}
