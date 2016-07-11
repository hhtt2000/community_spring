package com.demo.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;

	private static String namespace = "com.demo.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> list(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".list", cri);
	}

	@Override
	public int countList(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".countList", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("bno", bno);
		map.put("amount", amount);
		
		session.update(namespace + ".updateReplyCnt", map);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(namespace + ".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(namespace + ".getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		session.delete(namespace + ".deleteAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("bno", bno);
		map.put("fullName", fullName);
		
		session.insert(namespace + ".replaceAttach", map);
	}

	@Override
	public BoardCategoryVO getBoardCategory(String boardType) throws Exception {
		return session.selectOne(namespace + ".getBoardCategory", boardType);
	}

	@Override
	public List<BoardCategoryVO> getBoardCategories() throws Exception {
		return session.selectList(namespace + ".getBoardCategories");
	}

	@Override
	public List<BoardVO> getNotice(String boardType) throws Exception {
		return session.selectList(namespace + ".getNotice", boardType);
	}

	@Override
	public String getBoardAuth(String boardType) throws Exception {
		return session.selectOne(namespace + ".getBoardAuth", boardType);
	}

}
