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

import board.boardFile.model.BoardFileVo;
import board.boardFile.service.BoardFileService;
import board.boardFile.service.BoardFileServiceInf;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/boardFileDownload")
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("boardFileDownloadboardFileDownload===========================");
		String f_name = request.getParameter("f_name");
		
		response.setHeader("Content-Disposition", "attachment; filename="+f_name+"");
		response.setContentType("application/octet-stream");
		
		int f_id = Integer.parseInt(request.getParameter("f_id"));
		
		BoardFileServiceInf boardFileService = new BoardFileService();
		//FileUtil.fileUploadPath : sally.png
		// 파라미터로 파일명이 제공이 된다고 가정	

		
		//fileDownLoad?fileName=sally.png
		
		//http://localhost:8180/fileDownload?fileName=sally.png
		
		//"D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload\sally.png";
		System.out.println(boardFileService.getFileById(f_id));
		
		BoardFileVo boardFileVo = boardFileService.getFileById(f_id);
		String file = boardFileVo.getF_file();
		
		System.out.println("file========================"+file);
		
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
		
		System.out.println("파일다운완료!============================");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
