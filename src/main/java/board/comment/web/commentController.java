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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.comment.model.CommentVo;
import board.comment.service.CommentService;
import board.comment.service.CommentServiceInf;
import board.student.model.StudentVo;

@RequestMapping("/comment")
@Controller("commmentController")
public class commentController {
	
	@Resource(name="commentService")
	private CommentServiceInf commentService;
	
	
	@RequestMapping("/commentList")
	 public String commentList(@RequestParam Map<String,String> param, Model model) {

		//!Integer.parseInt(param.get("w_id")));---------------------------------------------------------------
		List<CommentVo> commentList = commentService.getAllComments(Integer.parseInt(param.get("w_id")));
		
		model.addAttribute("commentList",commentList);
		model.addAttribute("commentCnt",commentList.size());
	
		return "/comment/commentList";
	 }
	
	@RequestMapping("/commentCreate")
	@ResponseBody
	 public ModelAndView commentCreate(@RequestParam Map<String,String> param, Model model, HttpSession session) {
	
		System.out.println("**CommentCreateServlet commentVo===============================");
		//!Integer.parseInt(param.get("w_id")));---------------------------------------------------------------

		StudentVo studentVo = (StudentVo)session.getAttribute("studentVo");

		//파라미터 확인
		int w_id = Integer.parseInt(param.get("w_id"));
		String c_c = (param.get("c_cmt"));
		System.out.println("CommentCreateServlet commentVo======>"+w_id);
		System.out.println("CommentCreateServlet commentVo======>"+c_c);
		
		
		
		CommentVo commentVo = new CommentVo();
		//파라미터로 받은 값을 vo에 설정
		commentVo.setW_id(w_id);
		commentVo.setStd_id(studentVo.getStd_id());
		commentVo.setC_cmt(param.get("c_cmt"));
		commentVo.setC_regdt(new Date());
		System.out.println("CommentCreateServlet commentVo======>"+commentVo);
		
		//댓글 insert
		commentService.insertComment(commentVo);
		List<CommentVo> commentList = commentService.getAllComments(w_id);
		
		model.addAttribute("commentList",commentList);
		model.addAttribute("commentCnt",commentList.size());
		
		//return "commentList";
		return new ModelAndView("/comment/commentList");
		
	 }
	
	@RequestMapping("/commentDelete")
	 public ModelAndView commentDelete(@RequestParam Map<String,String> param, Model model) {
		
		int w_id = Integer.parseInt(param.get("w_id")); //  --comment만 반환파면 필요없다 --? 반환안되는 중
		
		//댓글 번호
		int c_id = Integer.parseInt(param.get("c_id"));

		CommentVo commentVo = new CommentVo();

		//파라미터로 받은 값을 vo에 설정
		//writeVo.setW_parent(w_parent);--답글클릭해서할때만 set한다
		commentVo.setC_delny("Y");
		commentVo.setC_cmt("삭제된 댓글입니다.");
		commentVo.setC_id(c_id);
	
		System.out.println("commentDelete ==========>"+commentVo);
	
		//게시글 update
		commentService.updateComment(commentVo);
		
		//게시글 조회 화면으로 
		model.addAttribute("w_id", w_id);
		return new ModelAndView("redirect:/write/writeDetail");
	 }
	
}
