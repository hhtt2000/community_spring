package com.demo.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.demo.domain.ClaimVO;
import com.demo.persistence.ClaimDAO;

@Service
public class ClaimServiceImpl implements ClaimService {
	
	@Inject
	ClaimDAO dao;

	@Override
	public void register(ClaimVO vo) throws Exception {
		dao.create(vo);
	}

}
