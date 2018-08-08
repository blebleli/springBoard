package board.write.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.board.model.BoardVo;
import board.board.service.BoardServiceInf;
import board.write.model.WriteVo;
import board.write.service.WriteService;
import board.write.service.WriteServiceInf;

@RequestMapping("/write")
@Controller("writeController")
public class writeController {

	@Resource(name="writeService")
	private WriteServiceInf writeService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	
	@RequestMapping("/writeList")
	 public String writeView(@RequestParam Map<String,String> param, Model model) {

		//선택한 게시판번호 가져와 name set
		int b_id = Integer.parseInt(param.get("b_id"));
		BoardVo boardVo= boardService.getBoardById(b_id);
	
		//pagenation
		String pageStr = param.get("page");
		int page = pageStr == null? 1 : Integer.parseInt(pageStr);
		String pageSizeStr = param.get("pageSize");
		int pageSize = pageSizeStr == null? 10 : Integer.parseInt(pageSizeStr);	
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		paramMap.put("b_id", b_id);
		
		//답글형태 글목록
		Map<String, Object>resultMap = writeService.getWriteView(paramMap);
		
		//게시판 페이지 리스트
		List<WriteVo> writeList = (List<WriteVo>)resultMap.get("pageList");
		
		//페이지 네비게이션 문자열
		String pageNavi = (String) resultMap.get("pageNavi");
		
		model.addAttribute("b_id", b_id);
		model.addAttribute("boardName",boardVo.getB_name());
		model.addAttribute("writeList",writeList);
		model.addAttribute("pageNavi", pageNavi);

		 return "writeList";
	 }

	@RequestMapping("/writeDetail")
	 public String writeDetail(@RequestParam Map<String,String> param, Model model) {
	
		WriteVo writeVo = 
		writeService.getWriteById(Integer.parseInt(param.get("w_id")));
	
		model.addAttribute("writeVo", writeVo);
		
		return "writeDetail";
	}
		
	@RequestMapping("/writeNew")
	 public String newWrite(@RequestParam Map<String,String> param, Model model) {
		int b_id = Integer.parseInt(param.get("b_id")); // 해당 게시판에 하기 위함
		model.addAttribute("b_id", b_id);
		
		if (param.get("w_parent")!=null)
		model.addAttribute("w_parent", Integer.parseInt(param.get("w_parent")));
		
		return "writeNew";
	}
	
	@RequestMapping("/writeUpdate")
	 public String updateWrite(@RequestParam Map<String,String> param, Model model) {
	
		WriteVo writeVo = 
		writeService.getWriteById(Integer.parseInt(param.get("w_id")));
		
		model.addAttribute("b_id", writeVo.getB_id());
		model.addAttribute("writeVo", writeVo);
		
		return "writeUpdate";
	}

	
	@RequestMapping("/writeCreate")
	 public String writeCreate(@RequestParam Map<String,String> param, Model model) {
		
		WriteVo writeVo = new WriteVo();
		int b_id= Integer.parseInt(param.get("b_id"));

	   //--답글여부 check
		if (param.get("w_parent") == "") {writeVo.setW_parent(0); }
		else{ writeVo.setW_parent(Integer.parseInt(param.get("w_parent"))); }
	
		writeVo.setStd_id   (param.get("std_id"      )); 		 	 
		writeVo.setB_id	    (b_id);  			 
		writeVo.setW_title  (param.get("w_title"     ));  			 
		writeVo.setW_content(param.get("smarteditor" ));  	 		 
		writeVo.setW_regdt  (new Date());

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
	
		//게시글 insert
		//writeService.insertWrite(writeVo);
		model.addAttribute("b_id", b_id);
		
		//writeList 뒤에 알아서 ?를 표시해준다 신기
		return "redirect:writeList";
	}

	
}
