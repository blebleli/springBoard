package board.write.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.board.model.BoardVo;
import board.write.model.WriteVo;
import board.write.service.WriteService;
import board.write.service.WriteServiceInf;

@RequestMapping("/write")
@Controller("writeController")
public class writeController {

	@Resource(name="writeService")
	private WriteServiceInf writeService;
	
	
	@RequestMapping("/writeList")
	 public String writeView(@RequestParam Map<String,String> param, Model model) {
		
	//	BoardServiceInf boardService = new BoardService();
		
		//선택한 게시판번호 가져와 name set
		int b_id = 1;	//Integer.parseInt(param.get("b_id"));
	//	BoardVo boardVo= boardService.getBoardById(b_id);
	//	model.addAttribute("boardName",boardVo.getB_name());
	
		//pagenation
		String pageStr = param.get("page");//request.getParameter("page");
		int page = pageStr == null? 1 : Integer.parseInt(pageStr);
		
		String pageSizeStr = param.get("pageSize");
		int pageSize = pageSizeStr == null? 10 : Integer.parseInt(pageSizeStr);	
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		paramMap.put("b_id", b_id);
		
		model.addAttribute("b_id", b_id);
	
		//List<WriteVo> writeList = writeService.getWriteView(paramMap);
		Map<String, Object>resultMap = writeService.getWriteView(paramMap);
		
		//게시판 페이지 리스트
		List<WriteVo> writeList = (List<WriteVo>)resultMap.get("pageList");
		model.addAttribute("writeList",writeList);
		
		//페이지 네비게이션 문자열
		String pageNavi = (String) resultMap.get("pageNavi");
		model.addAttribute("pageNavi", pageNavi);
		//interceptor 하면, model set안해도 되야한다.?
		 return "writeList";
	 }
	
	@RequestMapping("/writeCreate")
	 public String writeCreate(@RequestParam Map<String,String> param, Model model) {
		
		WriteVo writeVo = new WriteVo();
		int b_id= 1; //Integer.parseInt(param.get("b_id"));
		//파라미터로 받은 값을 vo에 설정
		//writeVo.setW_parent(w_parent);--답글클릭해서할때만 set한다
		writeVo.setW_parent (Integer.parseInt(param.get("w_parent"	 )));  
		writeVo.setStd_id   (param.get("std_id"      )); 		 	 
		writeVo.setB_id	    (b_id);  			 
		writeVo.setW_title  (param.get("w_title"     ));  			 
		writeVo.setW_content(param.get("smarteditor" ));  	 		 
		writeVo.setW_regdt  (new Date());

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
	
		//게시글 insert
		//writeService.insertWrite(writeVo);
		return "redirect:writeView"+b_id;
	}
		
	@RequestMapping("/writeDetail")
	 public String writeDetail(@RequestParam Map<String,String> param, Model model) {
		
		

		return "index";
	}
		
	
}
