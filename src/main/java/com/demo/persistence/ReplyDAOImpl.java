package com.demo.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.domain.Criteria;
import com.demo.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.demo.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> list(Integer bno, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("bno", bno);
		map.put("cri", cri);
		
		return session.selectList(namespace + ".list", map);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + ".count", bno);
	}
	
	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	@Override
	public int getBno(Integer rno) throws Exception {
		return session.selectOne(namespace + ".getBno", rno);
	}

	@Override
	public String getUserid(Integer rno) throws Exception {
		return session.selectOne(namespace + ".getUserid", rno);
	}

}
