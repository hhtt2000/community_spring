package com.demo.persistence;

import java.util.List;

import com.demo.domain.Criteria;
import com.demo.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> list(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	public int getBno(Integer rno) throws Exception;
	
	public String getUserid(Integer rno) throws Exception;
	
}
