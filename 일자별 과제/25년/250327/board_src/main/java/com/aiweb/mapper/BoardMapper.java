package com.aiweb.mapper;

import java.util.List;

import com.aiweb.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	public void insert(BoardVO board);
	public void insertSelectKey(BoardVO board);
	public BoardVO read(long bno);
	public int delete(long bno);
	public int update(BoardVO board);
	
}
