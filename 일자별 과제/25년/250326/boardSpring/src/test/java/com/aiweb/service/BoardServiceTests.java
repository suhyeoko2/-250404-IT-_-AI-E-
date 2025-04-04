package com.aiweb.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aiweb.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	//@Test
	public void testRemove() {
		if(service.remove(11)) {
			log.info("삭제성공");
		}else {
			log.info("삭제실패");
		}
	}
	
	//@Test
	public void testModify() {
		BoardVO board = service.get(12);
		board.setTitle("서비스 수정제목");
		
		if(service.modify(board)){
			log.info("수정성공");
		}else {
			log.info("수정실패");
		}		
	}
	
	@Test
	public void testGet() {
		log.info("@@@@" + service.get(100));		
	}	
	
	@Test
	public void testGetList() {
		service.getList().forEach(board->log.info(board));
	}	
	
	//@Test 
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("서비스 계층 신규");
		board.setContent("서비스 계층 신규내용");
		board.setTitle("Service");
		
		service.register(board);
		
		log.info("생성된 게시글 번호: "+ board.getBno());
	}
	
	@Test
	public void testExist() {
		assertNotNull(service);
		log.info("@@@@@" + service);
	}
}
