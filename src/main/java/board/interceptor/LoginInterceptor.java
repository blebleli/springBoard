package board.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name="studentService")
	StudentServiceInf studentService;

	//logger
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
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
