package com.demo.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.ClaimVO;
import com.demo.domain.SearchCriteria;
import com.demo.dto.ClaimDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.demo.mapper.AdminMapper";

	@Override
	public void createBoardCategory(BoardCategoryVO vo) throws Exception {
		session.insert(namespace + ".createBoardCategory", vo);
	}

	@Override
	public List<ClaimVO> getClaimList(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".getClaimList", cri);
	}

	@Override
	public void updateClaimStatus(ClaimDTO dto) throws Exception {
		session.update(namespace + ".updateClaimStatus", dto);
	}

	@Override
	public void deleteReplies(Integer bno) throws Exception {
		session.delete(namespace + ".deleteReplies", bno);
	}

	@Override
	public int countClaimList(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".countClaimList", cri);
	}

	@Override
	public ClaimVO readClaim(int cno) throws Exception {
		return session.selectOne(namespace + ".readClaim", cno);
	}

}
