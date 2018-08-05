package board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//logger
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	StudentServiceInf studentService = new StudentService();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler
			) throws Exception {
			
		HttpSession session = request.getSession();
		Object vo = session.getAttribute("studentVo");
		if (vo == null) {
			response.sendRedirect("login/login");
			return false;
		}
		return true;
	}


	
}
