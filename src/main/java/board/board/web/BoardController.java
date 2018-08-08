package board.board.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import board.board.model.BoardVo;
import board.board.service.BoardServiceInf;
import board.student.model.StudentVo;

@SessionAttributes
@RequestMapping("/board")
@Controller("boardController")
public class BoardController {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	
	@RequestMapping("/boardList")
	 public String boardView(Model model) {
		//interceptor 하면, model set안해도 되야한다.?
//		List<BoardVo> boardList = boardService.getAllBoards();
//		model.addAttribute("boardList",boardList);
		 return "boardList";
	 }
	
	@RequestMapping("/boardCreate")
	 public String boardCreate(@RequestParam("boardName") String boardName) {
		BoardVo boardVo = new BoardVo();
		
		//std_id interceptor 확인해야한다. 
		//파라미터로 받은 값을 vo에 설정
		boardVo.setB_name(boardName);
		boardVo.setStd_id("std_2");
		boardVo.setB_regdt(new Date());
		System.out.println("BoardCreateServlet boardVo======>"+boardVo);
		// insert
		//boardService.insertBoard(boardVo);
		 return "redirect:boardList"; //url로 redirect 하는 방법도 있다.
	 }
	
	@RequestMapping("/boardUpdate")
	 public String boardUpdate(@RequestParam Map<String,String> param) {
		
		BoardVo boardVo = new BoardVo();		
		boardVo.setB_id(Integer.parseInt(param.get("b_id")));
		boardVo.setB_name(param.get("boardName"));
		boardVo.setB_useny(param.get("b_useny"));
		
		System.out.println("BoardUpdateServlet boardVo======>"+boardVo);
		//update
		//boardService.updateBoard(boardVo);
		 return "redirect:boardList"; //url로 redirect 하는 방법도 있다.
		 
	 }
	
}
