package kr.or.ddit.createBoard;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import board.board.web.BoardController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml"})
public class BoradControllerTest {
	
	//스프링 모든 요청은 dispatcherServlet이 처리 
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc; 	//dispatcher servlet
	
	private Logger logger = LoggerFactory.getLogger(BoradControllerTest.class);
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//helloController spring 빈을 주입 받을 수 있다.
	@Resource(name="boardController")
	private BoardController boardController;
	
	@Test
	public void test() {
		assertNotNull(boardController);
	}

}
