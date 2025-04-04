package com.aiweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aiweb.domain.BoardVO;
import com.aiweb.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper; 
	
	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
	}

	@Override
	public BoardVO get(long bno) {
		return null;
	}

	@Override
	public boolean modify(BoardVO board) {
		return false;
	}

	@Override
	public boolean remove(long bno) {
		return false;
	}

	@Override
	public List<BoardVO> getList() {
		return null;
	}

}
