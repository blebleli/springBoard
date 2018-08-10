package board.login;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import board.interceptor.LoginInterceptor;
import board.student.dao.StudentDaoInf;
import board.student.model.StudentVo;
import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

@RequestMapping("/login")
@Controller("loginController")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="studentService")
	private StudentServiceInf studentService;
	
	
	@RequestMapping("/view")
	public String hello(Model model){ 
		return "login/login";
	}
	
	@RequestMapping("/loginProcess")
	public String loginProcess2(@ModelAttribute StudentVo vo, HttpSession session ){
		//vo에는 id pass 만 존재 ㅡ check 필요
		StudentVo result = studentService.loginCheck(vo);
		logger.debug("loginProcess2: " + vo.toString());

		if (result != null) {

			session.setAttribute("studentVo", result); //1234
			 return "redirect:/login/main";	
		}else {
			 return "redirect:/login/view";
		}
		
	}
	
	@RequestMapping("/main")
	public String main(@ModelAttribute StudentVo vo, HttpSession session ){
		return "main";
	}
	
	@RequestMapping("/logoutProcess")
	public String logoutProcess(HttpSession session) {
		studentService.logout(session);
		 return "redirect:/login/view";
	}
}
