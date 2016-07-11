package com.demo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Criteria;
import com.demo.domain.ReplyVO;
import com.demo.persistence.BoardDAO;
import com.demo.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	private UserService userService;
	
	private static final int UPOINT = 1;

	@Transactional
	@Override
	public void addReply(ReplyVO vo, String userid) throws Exception {
		replyDAO.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
		userService.updateUpointAndRole(userid, UPOINT);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replyDAO.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno, String userid) throws Exception {
		int bno = replyDAO.getBno(rno);
		replyDAO.delete(rno);
		boardDAO.updateReplyCnt(bno, -1);
		userService.updateUpointAndRole(userid, -UPOINT);
	}

	@Override
	public List<ReplyVO> listReply(Integer bno, Criteria cri) throws Exception {
		return replyDAO.list(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replyDAO.count(bno);
	}

}
