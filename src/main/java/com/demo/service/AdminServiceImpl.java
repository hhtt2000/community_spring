package com.demo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.ClaimVO;
import com.demo.domain.SearchCriteria;
import com.demo.dto.ClaimDTO;
import com.demo.persistence.AdminDAO;
import com.demo.persistence.BoardDAO;
import com.demo.persistence.ReplyDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDAO adminDAO;
	
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private ReplyService replyService;
	
	@Inject
	private BoardService boardService;

	@Override
	public void createBoardCategory(BoardCategoryVO vo) throws Exception {
		adminDAO.createBoardCategory(vo);
	}

	@Override
	public List<ClaimVO> getClaimList(SearchCriteria cri) throws Exception {
		return adminDAO.getClaimList(cri);
	}

	@Override
	public void updateClaimStatus(ClaimDTO dto) throws Exception {
		adminDAO.updateClaimStatus(dto);
	}

	@Transactional
	@Override
	public void deleteBoardByAdmin(Integer bno) throws Exception {
		BoardVO boardVO = boardDAO.read(bno);
		
		if(boardVO != null) {
			if(boardVO.getReplycnt() > 0) {
				adminDAO.deleteReplies(bno);
			}
			
			boardService.remove(bno, boardVO.getWriter());
		}
	}

	@Override
	public void deleteReplyByAdmin(Integer rno) throws Exception {
		String userid = replyDAO.getUserid(rno);
		
		if(userid != null) {
			replyService.removeReply(rno, userid);
		}
	}

	@Override
	public int countClaimList(SearchCriteria cri) throws Exception {
		return adminDAO.countClaimList(cri);
	}

	@Override
	public ClaimVO readClaim(int cno) throws Exception {
		return adminDAO.readClaim(cno);
	}

}
