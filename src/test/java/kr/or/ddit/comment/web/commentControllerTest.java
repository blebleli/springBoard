package kr.or.ddit.comment.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import board.comment.model.CommentVo;
import board.student.model.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml"})
@WebAppConfiguration
public class commentControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Test
	public void writeViewTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/comment/commentList")
									   .param("w_id", "50")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		
		List<CommentVo>  CommentList=  (List<CommentVo>)mav.getModel().get("commentList");
		
		assertEquals(6,CommentList.size());

	}
	
	
	@Test
	public void commentCreateTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/comment/commentList")
									   .param("w_id", "50")
									   .param("c_cmt", "댓글내용junit")
									   .param("std_id", "std_3")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		
		List<CommentVo>  CommentList=  (List<CommentVo>)mav.getModel().get("commentList");
		
		assertEquals(6,CommentList.size());

	}
	
	
	@Test
	public void commentDeleteTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/comment/commentDelete")
									   .param("c_id", "50")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		
		assertEquals("writeList", mav.getViewName());

	}
	

}
