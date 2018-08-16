package kr.or.ddit.createBoard.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import board.board.dao.BoardDaoInf;
import board.board.model.BoardVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class BoardDaoTest {


	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Test
	public void getAllBoards(){
		/***Given***/
		/***When***/
		List<BoardVo> boardList = boardDao.getAllBoards();
		
		for (BoardVo vo : boardList) {
			System.out.println(vo);
		}
		
		/***Then***/
		assertEquals(8, boardList.size());
		

	}
		
	@Test
	public void getBoardById(){
		/***Given***/
		int b_id = 1;
		
		/***When***/
		BoardVo boardVo = boardDao.getBoardById(b_id);
		System.out.println("selectStudentsById : "+ b_id);
		
		/***Then***/
		assertEquals("자유게시판", boardVo.getB_name());
	}
		
	@Test
	public void updateBoard (){
		
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setB_id(24);
		boardVo.setB_useny("Y");
		boardVo.setB_name("JunitTest");
		/***When***/
		int update = boardDao.updateBoard(boardVo);
		
		/***Then***/
		assertEquals(1, update);
	}
		

	public void insertBoard(){
		/***Given***/
	  	BoardVo boardVo = new BoardVo();
		boardVo.setB_id(4);
		boardVo.setStd_id("std_2");
		boardVo.setB_regdt(new Date());
		boardVo.setB_name("test");
	  	
		/***When***/
		int insert = boardDao.insertBoard(boardVo);
		/***Then***/
		assertEquals(1, insert);

	}
		
}
