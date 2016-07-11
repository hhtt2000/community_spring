package com.demo.persistence;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.ClaimVO;
import com.demo.domain.SearchCriteria;
import com.demo.dto.ClaimDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class AdminDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminDAOTest.class);
	
	@Inject
	private AdminDAO dao;
	
	@Test
	public void testCreateBoardCategory() throws Exception {
		BoardCategoryVO vo = new BoardCategoryVO();
		vo.setBoardType("game");
		vo.setBoardName("게임게시판");
		vo.setRole("user_1");
		
		dao.createBoardCategory(vo);
		
	}
	
	@Test
	public void testGetClaimList() throws Exception {
		SearchCriteria cri = new SearchCriteria();
//		cri.setSearchType("tc");
//		cri.setKeyword("신고");
		
		List<ClaimVO> list = dao.getClaimList(cri);
		
		for(ClaimVO claim : list) {
			logger.info("claim list: " + claim.toString());
		}
		
	}
	
	@Test
	public void testUpdateClaimStatus() throws Exception {
		ClaimDTO dto = new ClaimDTO();
		dto.setCno(2);
		dto.setStatus("read");
		
		dao.updateClaimStatus(dto);
		
	}
	
	@Test
	public void testDeleteReplies() throws Exception {
		dao.deleteReplies(462572);
	}
	
}
