package com.demo.service;

import java.util.List;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;

public interface BoardService {

	public void register(BoardVO board, String userid) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno, String userid) throws Exception;
	
	public List<BoardVO> list(SearchCriteria cri) throws Exception;
	
	public int countList(SearchCriteria cri) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	
	public BoardCategoryVO getBoardCategory(String boardType) throws Exception;
	
	public List<BoardCategoryVO> getBoardCategories() throws Exception;
	
	public String getBoardMinAuthority(String boardType) throws Exception;
	
}
