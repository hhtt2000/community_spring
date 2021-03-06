package com.demo.service;

import java.util.List;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.ClaimVO;
import com.demo.domain.SearchCriteria;
import com.demo.dto.ClaimDTO;

public interface AdminService {

	public void createBoardCategory(BoardCategoryVO vo) throws Exception;
	
	public List<ClaimVO> getClaimList(SearchCriteria cri) throws Exception;
	
	public void updateClaimStatus(ClaimDTO dto) throws Exception;
	
	public void deleteBoardByAdmin(Integer bno) throws Exception;
	
	public void deleteReplyByAdmin(Integer rno) throws Exception;

	public int countClaimList(SearchCriteria cri) throws Exception;

	public ClaimVO readClaim(int cno) throws Exception;
	
}
