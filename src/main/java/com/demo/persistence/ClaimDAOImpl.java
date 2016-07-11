package com.demo.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.domain.ClaimVO;

@Repository
public class ClaimDAOImpl implements ClaimDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.demo.mapper.ClaimMapper";

	@Override
	public void create(ClaimVO vo) throws Exception {
		// rno 기본값이 어떻게 설정되는지에 따라 에러 발생할 수도...
		session.insert(namespace + ".create", vo);
	}

	@Override
	public ClaimVO read(Integer cno) throws Exception {
		return session.selectOne(namespace + ".read", cno);
	}

	@Override
	public void update(ClaimVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer cno) throws Exception {
		session.delete(namespace + ".delete", cno);
	}

}
