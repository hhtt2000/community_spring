package com.demo.persistence;

import java.util.List;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> list(SearchCriteria cri) throws Exception;
	
	public int countList(SearchCriteria cri) throws Exception;
	
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	
	public void updateViewCnt(Integer bno) throws Exception;
	
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	
	public void deleteAttach(Integer bno) throws Exception;
	
	public void replaceAttach(String fullName, Integer bno) throws Exception;
	
	public BoardCategoryVO getBoardCategory(String boardType) throws Exception;
	
	public List<BoardCategoryVO> getBoardCategories() throws Exception;
	
	public List<BoardVO> getNotice(String boardType) throws Exception;
	
	public String getBoardAuth(String boardType) throws Exception;
	
}
