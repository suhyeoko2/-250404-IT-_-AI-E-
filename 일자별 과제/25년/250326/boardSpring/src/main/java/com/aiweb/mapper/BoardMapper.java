package com.aiweb.mapper;

import java.util.List;

import com.aiweb.domain.BoardVO;


public interface BoardMapper {
	//게시판 paging 처리할 때 원하는 값만 받아오기.
	//@Select("select *from tbl_board where bno>0)")
	
	public List<BoardVO> getList();
	public void insert(BoardVO board);
	public void insertSelectKey(BoardVO board);
	public BoardVO read(long bno);
	public int delete(long bno);                  //삭제한 개수를 받음
	public int update(BoardVO board);                //업데이트한 개수를 받음 
	
}
