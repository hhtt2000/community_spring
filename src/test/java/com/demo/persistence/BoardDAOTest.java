package com.demo.persistence;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject
	private BoardDAO dao;
	
	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("쇼생크 작성");
		board.setContent("...작성...");
		board.setWriter("user01");
		board.setBoardType("free");
		board.setNotice("f");
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(20).toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}
	
	@Test
	public void testList() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setSearchType("tc");
		cri.setKeyword("페이징");
		
		List<BoardVO> list = dao.list(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
		}
		
		logger.info("count: " + dao.countList(cri));
	}
	
	@Test
	public void testURI() {
		UriComponents uriComponent = 
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponent.toString());
	}
	
	@Test
	public void testURI2() {
		UriComponents uriComponent = 
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponent.toString());
	}
	
	@Test
	public void testAddAttach() throws Exception {
		// 테스트 시, last_insert_id() 수정 필요
		dao.addAttach("example");
	}
	
	@Test
	public void testGetBoardName() throws Exception {
		BoardCategoryVO boardCategory = dao.getBoardCategory("free");
		
		logger.info("boardName: "+boardCategory.getBoardName());
		logger.info("boardType: "+boardCategory.getBoardType());
	}
	
	@Test
	public void testGetNotice() throws Exception {
		List<BoardVO> list = dao.getNotice("free");
		
		for(BoardVO notice : list) {
			logger.info("notice: "+notice);
		}
	}
	
	@Test
	public void testGetBoardAuth() throws Exception {
		String boardAuth = dao.getBoardAuth("free");
		
		String authLvl = boardAuth.substring(boardAuth.lastIndexOf("_")+1);
		
		logger.info("board auth level: "+authLvl);
	}
}
