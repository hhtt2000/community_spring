package com.demo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.SearchCriteria;
import com.demo.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	private UserService userService;
	
	private static final int UPOINT = 5;

	@Transactional
	@Override
	public void register(BoardVO board, String userid) throws Exception {
		boardDAO.create(board);
		
		userService.updateUpointAndRole(userid, UPOINT);
		
		String[] files = board.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			boardDAO.addAttach(fileName);
		}
		
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		boardDAO.updateViewCnt(bno);
		return boardDAO.read(bno);
	}

	@Transactional
	@Override
	public void modify(BoardVO board) throws Exception {
		boardDAO.update(board);
		
		Integer bno = board.getBno();
		
		boardDAO.deleteAttach(bno);
		
		String[] files = board.getFiles();
		
		if(files == null) return;
		
		for(String fileName : files) {
			boardDAO.replaceAttach(fileName, bno);
		}
	}

	@Transactional
	@Override
	public void remove(Integer bno, String userid) throws Exception {
		boardDAO.deleteAttach(bno);
		boardDAO.delete(bno);
		userService.updateUpointAndRole(userid, -UPOINT);
	}

	@Transactional
	@Override
	public List<BoardVO> list(SearchCriteria cri) throws Exception {
		List<BoardVO> list = boardDAO.getNotice(cri.getBoardType());
		list.addAll(boardDAO.list(cri));
		return list;
	}

	@Override
	public int countList(SearchCriteria cri) throws Exception {
		return boardDAO.countList(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return boardDAO.getAttach(bno);
	}

	@Override
	public BoardCategoryVO getBoardCategory(String boardType) throws Exception {
		return boardDAO.getBoardCategory(boardType);
	}

	@Override
	public List<BoardCategoryVO> getBoardCategories() throws Exception {
		return boardDAO.getBoardCategories();
	}

	@Override
	public String getBoardMinAuthority(String boardType) throws Exception {
		return boardDAO.getBoardAuth(boardType);
	}

}
