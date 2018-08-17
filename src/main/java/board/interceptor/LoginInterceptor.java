package board.interceptor;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import board.student.service.StudentServiceInf;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name="studentService")
	StudentServiceInf studentService;

	//logger
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//濡쒓렇�씤 �긽�깭媛� �븘�땲�씪硫� , �뒘寃⑤궡�젮怨� intercept �븯�뒗寃�	
		HttpSession session = request.getSession();
		
		Object vo = session.getAttribute("studentVo");
		if (vo == null) {
			response.sendRedirect("/login/view");
			return false;
		}
		
		System.out.println("이용가능: " + request.getContentLength());
		if(request.getContentLength() > 0 && request.getMethod().equals("POST") && request.getRequestURI().contains("/write/imageUpload")) {

			InputStream is = request.getInputStream();
			int total = 0;
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			//numRead = is.read(b,0,b.length);
			while((numRead = is.read(b,total,4096)) != -1) {
				total += numRead;
			}
			if(total > 0) {
				request.setAttribute("image", b);
			}
			if(is != null) {
				is.close();
			}
		}
		return true;
	}
}
