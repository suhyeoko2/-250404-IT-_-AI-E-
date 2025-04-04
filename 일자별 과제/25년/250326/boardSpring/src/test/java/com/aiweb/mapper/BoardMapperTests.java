package com.aiweb.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aiweb.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
 	@Setter(onMethod_ =@Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testUpdate() {
		BoardVO board = mapper.read(7);
		board.setTitle("수정된 타이틀");
		log.info("@@@ 수정된 갯수:" + mapper.update(board));
	}	
	
	//@Test
	public void testDelete() {
		log.info("@@@@ 삭제 개수:" + mapper.delete(1)); 
	}	
	
    //@Test
	public void testRead() {
		BoardVO board = mapper.read(6);		
		log.info(board);
	}	
	
   //@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("신규 test");
		board.setContent("신규 내용");
		board.setWriter("newuser01");
		
		  mapper.insert(board);
		// mapper.insertSelectKey(board);
		log.info("@@@@@"  + board);		
	}	
	
	//@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("신규 test");
		board.setContent("신규 내용");
		board.setWriter("newuser01");
		
		mapper.insert(board);
		log.info("@@@@@"  + board);	
	}
	
	//@Test
	public void testGetList() {
		//람다함수
			
		mapper.getList().forEach(board->log.info("@@@ "+board));		
	}
//		List<BoardVO> list = mapper.getList();  //mapper가 resultset의 역할
//		
//		for(BoardVO tmp :list )
//			log.info("=====>" + tmp);
//	 	}
	}			
