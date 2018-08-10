package board.boardFile.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.boardFile.model.BoardFileVo;
import board.boardFile.service.BoardFileService;
import board.boardFile.service.BoardFileServiceInf;

@RequestMapping("/file")
@Controller("fileController")
public class fileController {

	private Logger logger = LoggerFactory.getLogger(fileController.class);


	@RequestMapping("/upload")
	public String multipartUpload(  @RequestPart("files")MultipartFile file,
									BoardFileVo files,
									MultipartHttpServletRequest request,
									Model model,
									@RequestParam Map<String,String> param) throws IllegalStateException, IOException{

		
		for (MultipartFile f: files.getFiles()) {
			logger.debug("{}","f.getOriginalFilename() : "+f.getOriginalFilename());
		}

		String path = FileUtil.fileUploadPath;

		
		for(MultipartFile f : files.getFiles()){
			if (null != f && f.getSize()> 0) {
			String fileName = UUID.randomUUID().toString();
			File uploadFile = new File(path + File.separator +fileName);
			f.transferTo(uploadFile);
			}
		
		}
		
		try {
			request.getPart("files");
			request.getParts();
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		int w_id =0;
//		if (param.get("w_id") == "") { w_id = 0; }
//		else{ w_id = Integer.parseInt(param.get("w_id")); }
		
		model.addAttribute("w_id", 50); //Integer.parseInt(param.get("w_id")));

		return "writeDetail";
	}
	
	public String multipartDown(@RequestPart("files")MultipartFile file,
								BoardFileVo files,
								MultipartHttpServletRequest request,
								Model model) throws IllegalStateException, IOException{

		return "writeDetail";
	}
	

}
