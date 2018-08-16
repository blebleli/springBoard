package kr.or.ddit.createBoard.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import board.board.model.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml"})
@WebAppConfiguration
public class BoradControllerTest {
	
	//스프링 모든 요청은 dispatcherServlet이 처리 
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void boardListTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(get("/board/boardList")).andReturn();
		
		/***When***/

		/***Then***/
		//viewname
		//속성들(studentList, totCnt, pageNavi)
		ModelAndView mav = result.getModelAndView();
		
		assertEquals("boardList",mav.getViewName());
	}
	
//	@Test
	public void boardCreateTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(get("/board/boardCreate")
									  .param("boardName", "boardNameTest")
									  .param("std_id", "std_3")
									  .param("b_regdt", "date")).andReturn();

		/***When***/
		
		ModelAndView mav = result.getModelAndView();
		
		BoardVo boardVo=  (BoardVo) mav.getModel().get("boardVo");
		/***Then***/
		//viewname
		//속성들(studentList, totCnt, pageNavi)
		assertEquals("boardNameTest",boardVo.getB_name());
	}
	
	@Test
	public void boardUpdateTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(get("/board/boardUpdate")
									  .param("b_id", "10")
									  .param("w_id", "10")
									  .param("boardName", "boardNameTest")
									  .param("b_useny", "N")).andReturn();
		/***When***/
		ModelAndView mav = result.getModelAndView();
	
		BoardVo boardVo=  (BoardVo) mav.getModel().get("boardVo");
		
		assertEquals("boardNameTest",boardVo.getB_name());
		
	}
	

	
	
}
