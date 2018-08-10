package board.boardFile.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;



public class FileDownloadView implements View{

	
	@Override
	public String getContentType() {
		return "application/octet-stream";
	}
	
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//fileNamd
		String fileName = (String)model.get("fileName");
		String originalFileName = (String)model.get("originalFileName");
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+originalFileName+"\"");
		response.setContentType("application/octet-stream");

		String file = FileUtil.fileUploadPath+ File.separator+fileName;
	
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
}
