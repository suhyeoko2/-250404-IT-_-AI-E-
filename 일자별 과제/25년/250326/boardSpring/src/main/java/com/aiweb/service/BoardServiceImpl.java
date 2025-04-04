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
//@RequiredArgsConstructor -->소스코드)private BoardMapper final mapper
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;		

	@Override
	public void register(BoardVO board) {
	//	mapper.insert(board);    // 0부터 시작
		log.info("service register.....");
		mapper.insertSelectKey(board);  // 생성된 번호 받음.
	}

	@Override
	public BoardVO get(long bno) {
		log.info("조회할 번호:" + bno);
		mapper.read(bno);
		return null;
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("수정할 번호:" + board.getBno());
		//mapper.modify();
		return mapper.update(board)==1;   //참인 경우 =1 
	}

	@Override
	public boolean remove(long bno) {
		log.info("삭제할 번호:" + bno);		
		return mapper.delete(bno)==1;
	}

	@Override 
	public List<BoardVO> getList() {          
		log.info(".........getList.....");
		return mapper.getList();      // JDBC의 Resultset과 비슷.
	}
}
