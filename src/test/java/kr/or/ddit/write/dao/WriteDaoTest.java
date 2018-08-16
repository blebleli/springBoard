package kr.or.ddit.write.dao;

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

import board.board.dao.BoardDaoInf;
import board.write.dao.WriteDao;
import board.write.dao.WriteDaoInf;
import board.write.model.WriteVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class WriteDaoTest {


	@Resource(name="writeDao")
	private WriteDaoInf writeDao;
	
	@Test
	public void getAllWrites(){
		
		/***Given***/
		int b_id=2;
		/***When***/
		List<WriteVo> writeVo = writeDao.getAllWrites(b_id);
		/***Then***/
		assertEquals(2,	writeVo.size());
	}

	@Test
	public void getWriteView(){
		/***Given***/
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totCnt", 3);

		//페이지 네비게이션 html 생성
		map.put("page",1);	
		map.put("pageSize",10);
		map.put("b_id",1);
				
		/***When***/
		List<WriteVo> writeVo = writeDao.getWriteView(map);
		/***Then***/
		assertEquals(10, writeVo.size());

	}

	@Test
	public void getWriteById(){
		/***Given***/
		int w_id = 1;
		/***When***/
		WriteVo writeVo = writeDao.getWriteById(w_id);
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
		int update = writeDao.updateWrite(writeVo);
		/***Then***/
		assertEquals(1, update);
	}

	@Test
	public void deleteWrite (){
		/***Given***/
		WriteVo writeVo = new WriteVo();
		writeVo.setW_delny("Y");
		writeVo.setW_id(2);
		int update = writeDao.deleteWrite(writeVo);
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
		int insert = writeDao.insertWrite(writeVo);
		
		/***Then***/
		assertEquals(1, insert);

	}

	@Test
	public void getWriteTotCnt(){
		/***Given***/
		int b_id = 1;

		/***When***/
		int cnt = writeDao.getWriteTotCnt(b_id);
		/***Then***/
		assertEquals(16, cnt);
	}
}
