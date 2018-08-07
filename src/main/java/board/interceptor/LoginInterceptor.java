package board.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import board.board.model.BoardVo;
import board.board.service.BoardService;
import board.board.service.BoardServiceInf;
import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//logger
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	StudentServiceInf studentService = new StudentService();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//로그인 상태가 아니라면 , 튕겨내려고 intercept 하는것	
		HttpSession session = request.getSession();
		
		Object vo = session.getAttribute("studentVo");
		if (vo == null) {
			response.sendRedirect("/login/view");
			return false;
		}

		return true;
	}


	
}
