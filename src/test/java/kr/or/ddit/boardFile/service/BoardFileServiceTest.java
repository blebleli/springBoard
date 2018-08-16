package kr.or.ddit.boardFile.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import board.boardFile.model.BoardFileVo;
import board.boardFile.service.BoardFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class BoardFileServiceTest {

	@Resource(name="fileService")
	private BoardFileService boardFileService;
	
	@Test
	public void getAllFiles() {

		/***Given***/
		int w_id = 1;
		/***When***/
		List<BoardFileVo> boardFileList = boardFileService.getAllFiles(w_id);
		/***Then***/
		assertEquals(3, boardFileList.size());
	}

	@Test
	public void getFileById() {
		/***Given***/
		int f_id=1;

		/***When***/
		BoardFileVo boardFileVo = boardFileService.getFileById(f_id);
		
		/***Then***/
		assertEquals(1, boardFileVo.getW_id());
	}

	//@Test
	public void insertFile() {
		/***Given***/
		BoardFileVo boardFileVo = new BoardFileVo();
		boardFileVo.setW_id(1);
		boardFileVo.setStd_id("std_25");
		boardFileVo.setF_path("junit test");
		boardFileVo.setF_name("junit test");
		boardFileVo.setF_file("junit test");
	
		/***When***/
		int insert = boardFileService.insertFile(boardFileVo);
		
		/***Then***/
		assertEquals(1, insert);
	}


}
