package kr.or.ddit.student.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import board.student.model.StudentVo;
import board.student.service.StudentServiceInf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml"})
@WebAppConfiguration
public class StudentControllerTest {

	//스프링 모든 요청은 dispatcherServlet이 처리 
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Resource(name="studentService")
	private StudentServiceInf studentService;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}
	
	@Test
	public void listTestTest() throws Exception {
		MvcResult result = mvc.perform(get("/student/listTest")).andReturn();
		
	
	
		ModelAndView mav = result.getModelAndView();
		
		List<StudentVo> studentList=  (List<StudentVo>)mav.getModel().get("studentVo");
		
		assertEquals(25,studentList.size());

	}

	
	@Test
	public void studentListTest() throws Exception {
		MvcResult result = mvc.perform(get("/student/list").param("page", "1")
				.param("pageSize", "10")).andReturn();
		
		ModelAndView mav = result.getModelAndView();

		assertEquals("student/list", mav.getViewName());
	}

	
	@Test
	public void detailTest() throws Exception {
		MvcResult result = mvc.perform(get("/student/detail")
				.param("std_id", "std_14")).andReturn();
								
		ModelAndView mav = result.getModelAndView();
		
		StudentVo studentVo=  (StudentVo)mav.getModel().get("studentVo");
		
		assertEquals("한수정",studentVo.getName());
	}
	

	
}
