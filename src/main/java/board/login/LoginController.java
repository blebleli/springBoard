package board.login;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.student.model.StudentVo;
import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

@RequestMapping("/login")
@Controller("loginController")
public class LoginController {
	
	StudentServiceInf studentService = new StudentService();

	@RequestMapping("/view")
	public String hello(Model model){ 
		return "login/login";
	}
	
	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess2(@ModelAttribute StudentVo vo, HttpSession session ){
		StudentVo result = studentService.loginCheck(vo);
		ModelAndView mav = new ModelAndView();
		if (result != null) {
			session.setAttribute("studentVo", vo);
			mav.setViewName("main");
			//성공메세지		
		}else {
			mav.setViewName("login/login");
			mav.addObject("msg","failure");
		}
		return mav;
	}
	
	@RequestMapping("/logoutProcess")
	public ModelAndView logoutProcess(HttpSession session) {
		studentService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		//mav.addObject("msg","logout");
		return mav;
	}
}
