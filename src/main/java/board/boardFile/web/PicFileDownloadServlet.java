package board.boardFile.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.student.model.StudentVo;
import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/pic")
public class PicFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//<img src="/pic?id=2 />
		int id = Integer.parseInt(request.getParameter("id"));
		
		//물리파일명을 이용하여 사진 다운로드 부터 진행
		HttpSession session = request.getSession();
		StudentVo studentVo = (StudentVo)session.getAttribute("studentVo");
		String std_id=studentVo.getStd_id();
		
		
		//사용자정보 조회
		//StudentServiceInf studentService = new StudentService();
		//StudentVo studentVo = studentService.getStudentById(std_id);
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+studentVo.getPic()+"\"");
		response.setContentType("application/octet-stream");
		
		
		//FileUtil.fileUploadPath : sally.png
		// 파라미터로 파일명이 제공이 된다고 가정		
		//fileDownLoad?fileName=sally.png
		
		//http://localhost:8180/fileDownload?fileName=sally.png
	
		//"D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload\sally.png";
		
		//중복될까봐 난수로 파일네임을 준다.
		String fileName = request.getParameter("fileName");
		String file = studentVo.getPicpath()+File.separator+studentVo.getPicname();
		
		//file 입출력을 위한 준비
		ServletOutputStream sos = response.getOutputStream();
		
		File f = new File(file);
		FileInputStream fis = new FileInputStream(f);
		byte[] b = new byte[512];
		int len =0;
		while((len = fis.read(b)) != -1 ){
			sos.write(b, 0, len);
		}

		sos.close();
		fis.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
