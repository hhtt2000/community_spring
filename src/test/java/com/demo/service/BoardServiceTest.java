package com.demo.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
	
	@Inject
	private BoardService service;
	
	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("포인트는?");
		board.setContent("...");
		board.setWriter("user00");
		board.setBoardType("free");
		
		service.register(board, board.getWriter());
	}
	
	@Test
	public void testRemove() throws Exception {
		service.remove(462832, "user00");
	}
	
	@Test
	public void testGetList() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setBoardType("free");

		List<BoardVO> list = service.list(cri);
		
		for(BoardVO board : list) {
			logger.info(board.getBno()+": "+board);
		}
	}

}
