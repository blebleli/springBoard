package board.boardFile.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import board.boardFile.model.BoardFileVo;
import board.boardFile.service.BoardFileService;
import board.boardFile.service.BoardFileServiceInf;
import board.student.model.StudentVo;
import board.write.model.WriteVo;
import board.write.service.WriteService;
import board.write.service.WriteServiceInf;

/**
 * Servlet implementation class BoardFileUpload
 */
@WebServlet("/boardFileUpload")
@MultipartConfig(maxFileSize=1024*1000*5, maxRequestSize=1024*1000*16)

public class BoardFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(BoardFileUploadServlet.class);

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		System.out.println("BoardFileUploadServlet===================================================");
		//parameter : userId, uploadFile
		//일반 텍스트 파라미터는 request.getPARAMETER를 통해서 얻을 수 있다.
		//파일의 경우 Request.getpart 를 통해서 얻을 수 있다.
		
		//logger.debug("getContentType"+request.getContentType());
		//logger.debug("userId : "+ request.getParameter("userId"));
		//logger.debug("uploadFile : "+ request.getParameter("uploadFile"));
	
		//getPart
		Part uploadFilePart = request.getPart("uploadFile");
		
		//logger.debug("uploadFilePart.getName() : "+uploadFilePart.getName());
		
		//file이름에 대한 정보를 획득 : getHeader("Content - Dispositi on");
		//
		String contentDisposition =uploadFilePart.getHeader("Content-Disposition");
		logger.debug("contentDisposition : " + contentDisposition);

		String fileName = FileUtil.getFileName(contentDisposition);
		logger.debug("fileName : ======="+ fileName);
		
		//파일 저장 경로 설정
		//실무 : 별도 storage
		//개발환경 :  임의영역 (서버 변경시, 재기동시 업로드 파일 삭제됨)
		//fileUpload 디렉토리에 임의로 작성
		//D:\A_TeachingMaterial\7.JspSpring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps
		//dopost 메소드 안에서 applicaio(servletContext)객체를 구한다.
		
		//1. request.getServletContext()
		//2. getServletContext() (서블릿에서 제공해주는 메소드)
		
//		//임의의 배포경로
//		ServletContext servletContext = getServletContext();
//		String path = servletContext.getRealPath("/fileUpload");
//		
		
	//	고정된 영역으로 배포
		String path = FileUtil.fileUploadPath;
		
		
		//파일사이즈가 정상적인 경우에만 업로드를 수행한다
		if(uploadFilePart.getSize() > 0){
			//uploadFilePart.write(path + File.separator+fileName); //os환경에 맞게 separate 해준다
			//사용자가 업로드한 실제 파일명은 디비상에 저장하고, 물리적 파일명은 임의로 적용
						//UUID -- 실제파일명을 지정해야지만, 원했던이름으로 원복할 수 있다.
			uploadFilePart.write(path + File.separator+UUID.randomUUID().toString());
			uploadFilePart.delete();
		}	
		
		
	
		//
		HttpSession session = request.getSession();
		StudentVo studentVo = (StudentVo)session.getAttribute("studentVo");
		String std_id=studentVo.getStd_id();
		
		
		//파라미터 확인
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		int w_id = Integer.parseInt(request.getParameter("w_id"));
		//byte[] f_file = (byte[])request.getParameter("f_file");
	
		BoardFileVo boardFileVo = new BoardFileVo();
		

		//파라미터로 받은 값을 vo에 설정
		boardFileVo.setW_id(w_id);
		boardFileVo.setStd_id(std_id);
	    boardFileVo.setF_file(path+File.separator+fileName);
		boardFileVo.setF_path(path);
		boardFileVo.setF_name(fileName);
		System.out.println("BoardFileUploadServlet u_id======>"+boardFileVo);
		
		
		//5개인지 확인해야한다 =================================
		
		BoardFileServiceInf boardFileService = new BoardFileService();
		boardFileService.insertFile(boardFileVo);
		
		WriteServiceInf writeService = new WriteService();
		WriteVo writeVo = writeService.getWriteById(w_id);
		
		writeVo.setW_filenum(writeVo.getW_filenum()+1);
		writeService.updateWriteFile(writeVo);
	
		System.out.println("업로드완료======>"+boardFileVo);

		//게시글 조회 화면으로 
		response.sendRedirect("/writeDetailView?b_id="+b_id+"&w_id="+w_id);
		
		
	}
}

