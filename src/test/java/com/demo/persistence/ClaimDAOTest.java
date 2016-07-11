package com.demo.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.ClaimVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ClaimDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ClaimDAOTest.class);
	
	@Inject
	private ClaimDAO dao;
	
	@Test
	public void testCreateClaim() throws Exception {
		ClaimVO vo = new ClaimVO();
		vo.setClaimer("user00");
		vo.setTitle("이런...");
		vo.setContent("내용...");
		vo.setBno(462832);
		vo.setUrl("http://localhost:8181/board/read?page=1&perPageNum=10&boardType=free&searchType&keyword&bno=462832");
		
		dao.create(vo);
	}
	
	@Test
	public void testReadClaim() throws Exception {
		ClaimVO vo = dao.read(15);
		if(vo != null) {
			logger.info("read from claim table: " + vo.toString());
		}
	}
	
	@Test
	public void testUpdateClaim() throws Exception {
		ClaimVO vo = dao.read(1);
		vo.setTitle("신고 수정...");
		
		dao.update(vo);
	}
	
	@Test
	public void testDeleteClaim() throws Exception {
		dao.delete(1);
	}

}
