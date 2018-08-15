package board.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import board.board.web.BoardController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class BoradControllerTest {
	
	//스프링 모든 요청은 dispatcherServlet이 처리 
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc; 	//dispatcher servlet
	
	private Logger logger = LoggerFactory.getLogger(BoradControllerTest.class);
	
	private MockMvc mvc;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//helloController spring 빈을 주입 받을 수 있다.
	@Resource(name="boardController")
	private BoardController boardController;
	
	@Test
	public void test() {
		assertNotNull(boardController);
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
	
	@Test
	public void boardCreateTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(get("/board/boardCreate")
									  .param("boardName", "1")).andReturn();

		/***When***/
		
		/***Then***/
		//viewname
		//속성들(studentList, totCnt, pageNavi)
		ModelAndView mav = result.getModelAndView();
		
		assertEquals("student/detail",mav.getViewName());
	

	}
	

}
