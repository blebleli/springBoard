package board.comment.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.comment.model.CommentVo;
import board.comment.service.CommentServiceInf;
import board.student.model.StudentVo;

@RequestMapping("/comment")
@Controller("commmentController")
public class commentController {
	
	@Resource(name="commentService")
	private CommentServiceInf commentService;
	
	@RequestMapping("/commentList")
	 public String commentList(@RequestParam Map<String,String> param, Model model) {

		List<CommentVo> commentList = commentService.getAllComments(Integer.parseInt(param.get("w_id")));
		
		model.addAttribute("commentList",commentList);
		model.addAttribute("commentCnt",commentList.size());
	
		return "/comment/commentList";
	 }
	
	@RequestMapping("/commentCreate")
	 public String commentCreate(@RequestParam Map<String,String> param, Model model, HttpSession session) {

		//request.setCharacterEncoding("utf-8");
		StudentVo studentVo = (StudentVo)session.getAttribute("studentVo");
		System.out.println("CommentCreateServlet u_id======>"+studentVo.getStd_id());
		
		//파라미터 확인
		int b_id = Integer.parseInt(param.get("b_id"));
		int w_id = Integer.parseInt(param.get("w_id"));
		String c_cmt = param.get("c_cmt");
		
		CommentVo commentVo = new CommentVo();
		
		//파라미터로 받은 값을 vo에 설정
		commentVo.setW_id(w_id);
		commentVo.setStd_id(studentVo.getStd_id());
		commentVo.setC_cmt(c_cmt);
		commentVo.setC_regdt(new Date());
		System.out.println("CommentCreateServlet commentVo======>"+commentVo);
		
		//댓글 insert
		//commentService.insertComment(commentVo);

		model.addAttribute("w_id", w_id);
		return "redirect:writeList";
	 }
	
}
