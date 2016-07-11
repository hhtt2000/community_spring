package com.demo.service;

import java.util.List;

import com.demo.domain.Criteria;
import com.demo.domain.ReplyVO;

public interface ReplyService {

	public void addReply(ReplyVO vo, String userid) throws Exception;
	
	public List<ReplyVO> listReply(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno, String userid) throws Exception;
	
}
