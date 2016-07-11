package com.demo.persistence;

import com.demo.domain.ClaimVO;

public interface ClaimDAO {

	public void create(ClaimVO vo) throws Exception;
	
	public ClaimVO read(Integer cno) throws Exception;
	
	public void update(ClaimVO vo) throws Exception;
	
	public void delete(Integer cno) throws Exception;
	
}
