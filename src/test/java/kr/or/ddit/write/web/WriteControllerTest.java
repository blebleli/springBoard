package kr.or.ddit.write.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import board.student.model.StudentVo;
import board.write.model.WriteVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml"})
@WebAppConfiguration
public class WriteControllerTest {

	//스프링 모든 요청은 dispatcherServlet이 처리 
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void writeViewTest() throws Exception {
		
		
		MvcResult result = mvc.perform(get("/write/writeList")
									   .param("b_id", "12")
									   .param("page", "1")
									   .param("pageSize", "10")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		
		String boardVo=  (String)mav.getModel().get("boardName");
		
		assertEquals("test입니다",boardVo);

	}

	
	@Test
	public void writeDetailTest() throws Exception {
		
		MvcResult result = mvc.perform(get("/write/writeDetail")
									   .param("w_id", "1")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		
		WriteVo writeVo=  (WriteVo) mav.getModel().get("writeVo");

		assertEquals("수정 junit test",writeVo.getW_title());

	}
	
	@Test
	 public void newWriteTest() throws Exception {
		MvcResult result = mvc.perform(get("/write/writeNew")
				   			  .param("b_id", "1")).andReturn();

		ModelAndView mav = result.getModelAndView();

		assertEquals("writeNew", mav.getViewName());
	}
	
	
	
	@Test
	 public void updateWrite() throws Exception {
		MvcResult result = mvc.perform(get("/write/updateIndex")
				   			  .param("w_id", "1")).andReturn();

		ModelAndView mav = result.getModelAndView();

		assertEquals("writeUpdate", mav.getViewName());
	
		int b_id=  (int) mav.getModel().get("b_id");

		assertEquals(3,b_id);
	}
	
	

	@Test
	 public void writeDelete() throws Exception {
		MvcResult result = mvc.perform(get("/write/writeDelete")
							  .param("b_id", "1")
			 				  .param("w_id", "1")
			 				  .param("std_id", "std_2")).andReturn();

		ModelAndView mav = result.getModelAndView();
	
		assertEquals("writeList", mav.getViewName());

	}

	
	/*
	@RequestMapping("/writeCreate")
	 public String writeCreate(@RequestParam Map<String,String> param, 
			 
								 Model model,
								 BoardFileVo files,
								 MultipartHttpServletRequest request,
								 @ModelAttribute StudentVo studentVo) 
					   throws IllegalStateException, IOException{
		
		WriteVo writeVo = new WriteVo();
		
		int b_id= Integer.parseInt(param.get("b_id"));
		String content = request.getParameter("smarteditor");
	    //--답글여부 check
		if (param.get("w_parent").equals("")) {writeVo.setW_parent(0); }
		else{ writeVo.setW_parent(Integer.parseInt(param.get("w_parent"))); }
		
		writeVo.setStd_id   (studentVo.getStd_id()); 		 	 
		writeVo.setB_id	    (b_id);  			 
		writeVo.setW_title  (param.get("w_title"    ));  			 
		writeVo.setW_content(content);  	 		 
		writeVo.setW_regdt  (new Date());

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
		
		//게시글 insert
		writeService.insertWrite(writeVo);

		//파일부분---------------------------------------------------------------------------
		
		
		for (MultipartFile f: files.getFiles()) {
			logger.debug("{}","f.getOriginalFilename() : "+f.getOriginalFilename());
		}

		String path = FileUtil.fileUploadPath;

		
		for(MultipartFile f : files.getFiles()){
			if (null != f && f.getSize()> 0) {
			String fileName = UUID.randomUUID().toString();
			File uploadFile = new File(path + File.separator +fileName);
			f.transferTo(uploadFile);
			
			BoardFileVo boardFileVo = new BoardFileVo();
			boardFileVo.setW_id(writeVo.getW_id());
			System.out.println("파일업로드할때 w_id ------------------"+writeVo.getW_id());
			boardFileVo.setStd_id(studentVo.getStd_id());
			boardFileVo.setF_file(path);
			boardFileVo.setF_path(uploadFile.getAbsolutePath());
			boardFileVo.setF_name(f.getOriginalFilename());
			
			System.out.println("WriteCreateServlet boardFileVo============"+boardFileVo);
			//file insert
			fileService.insertFile(boardFileVo);
			}
		
		}
		
		try {
			request.getPart("files");
			request.getParts();
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
		model.addAttribute("b_id", b_id);
		
		//writeList 뒤에 알아서 ?를 표시해준다 신기
		return "writeList";
		//return "redirect:writeList";
	}
	
	
	
	@RequestMapping("/writeUpdate")
	 public String writeUpdate(@RequestParam Map<String,String> param, 
								 Model model,
								 BoardFileVo files,
								 MultipartHttpServletRequest request,
								 @ModelAttribute StudentVo studentVo) 
					   throws IllegalStateException, IOException{
		
		int b_id= Integer.parseInt(param.get("b_id"));
		int w_id= Integer.parseInt(param.get("w_id"));
		
		WriteVo writeVo = writeService.getWriteById(w_id);
	
		writeVo.setW_title  (param.get("w_title"    ));  			 
		writeVo.setW_content(param.get("smarteditor"));  	 		 
		writeVo.setW_id(w_id);

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
		
		//게시글 update
		writeService.updateWrite(writeVo);

		
		//파일부분---------------------------------------------------------------------------
		
		
		for (MultipartFile f: files.getFiles()) {
			logger.debug("{}","f.getOriginalFilename() : "+f.getOriginalFilename());
		}

		String path = FileUtil.fileUploadPath;

		
		for(MultipartFile f : files.getFiles()){
			if (null != f && f.getSize()> 0) {
			String fileName = UUID.randomUUID().toString();
			File uploadFile = new File(path + File.separator +fileName);
			f.transferTo(uploadFile);
			
			BoardFileVo boardFileVo = new BoardFileVo();
			boardFileVo.setW_id(writeVo.getW_id());
			System.out.println("파일업로드할때 w_id ------------------"+writeVo.getW_id());
			boardFileVo.setStd_id(studentVo.getStd_id());
			boardFileVo.setF_file(path);
			boardFileVo.setF_path(uploadFile.getAbsolutePath());
			boardFileVo.setF_name(f.getOriginalFilename());
			
			System.out.println("WriteCreateServlet boardFileVo============"+boardFileVo);
			//file insert
			fileService.insertFile(boardFileVo);
			}
		
		}
		
		try {
			request.getPart("files");
			request.getParts();
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute("b_id", b_id);
		
		//writeList 뒤에 알아서 ?를 표시해준다 
		return "redirect:writeList";
	}
	
	
	
	@RequestMapping("/fileDown")
	public String fileDown(@RequestParam("fileName") String fileName, Model model){
		model.addAttribute("fileName", fileName);
		return "fileDownloadView";
	}
	*/
}
