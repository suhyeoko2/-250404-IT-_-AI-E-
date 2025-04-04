package com.aiweb.service;

import java.util.List;
import com.aiweb.domain.BoardVO;

public interface BoardService {
	
	public void register(BoardVO board);	
	public BoardVO get(long bno);
	public boolean modify(BoardVO board);
	public boolean remove(long bno);
	public List<BoardVO> getList();
}
