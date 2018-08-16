package kr.or.ddit.write.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import board.board.service.BoardServiceInf;
import board.write.model.WriteVo;
import board.write.service.WriteService;
import board.write.service.WriteServiceInf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class WriteServiceTest {

	@Resource(name="writeService")
	private WriteServiceInf writeService;
	
	@Test
	public void getAllWrites(){
		
		/***Given***/
		int b_id=2;
		/***When***/
		List<WriteVo> writeVo = writeService.getAllWrites(b_id);
		/***Then***/
		assertEquals(2,	writeVo.size());
	}

	@Test
	public void getWriteById(){
		/***Given***/
		int w_id = 1;
		/***When***/
		WriteVo writeVo = writeService.getWriteById(w_id);
		/***Then***/
		assertEquals(3, writeVo.getB_id());
	}

	@Test
	public void updateWrite (){

		/***Given***/
		WriteVo writeVo = new WriteVo();
		writeVo.setW_title("수정 junit test");
		writeVo.setW_content("수정 junit test");
		writeVo.setW_delny("N");
		writeVo.setW_id(1);

		/***When***/
		int update = writeService.updateWrite(writeVo);
		/***Then***/
		assertEquals(1, update);
	}

	@Test
	public void deleteWrite (){
		/***Given***/
		WriteVo writeVo = new WriteVo();
		writeVo.setW_delny("Y");
		writeVo.setW_id(2);
		int update = writeService.deleteWrite(writeVo);
		/***Then***/
		assertEquals(1, update);

	}

	//@Test
	public void insertWrite(){
		/***Given***/

		WriteVo writeVo = new WriteVo();
		writeVo.setStd_id("std_2");
		writeVo.setB_id(1);
		writeVo.setW_title("수정 junit test");
		writeVo.setW_content("수정 junit test");
		writeVo.setW_regdt(new Date());
	
		/***When***/
		int insert = writeService.insertWrite(writeVo);
		
		/***Then***/
		assertEquals(1, insert);

	}

	@Test
	public void getWriteTotCnt(){
		/***Given***/
		int b_id = 1;

		/***When***/
		int cnt = writeService.getWriteTotCnt(b_id);
		/***Then***/
		assertEquals(9, cnt);
	}
}
