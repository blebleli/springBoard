package board.write.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.model.BoardVo;
import board.board.service.BoardServiceInf;
import board.boardFile.model.BoardFileVo;
import board.boardFile.service.BoardFileServiceInf;
import board.boardFile.web.FileUtil;
import board.student.model.StudentVo;
import board.write.model.WriteVo;
import board.write.service.WriteServiceInf;

@RequestMapping("/write")
@Controller("writeController")
@SessionAttributes("studentVo")
public class writeController {

	private Logger logger = LoggerFactory.getLogger(writeController.class);
	
	
	@Resource(name="writeService")
	private WriteServiceInf writeService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Resource(name="fileService")
	private BoardFileServiceInf fileService;
	
	@RequestMapping("/writeList")
	 public String writeView(@RequestParam Map<String,String> param, Model model) {

		//선택한 게시판번호 가져와 name set
		int b_id = Integer.parseInt(param.get("b_id"));
		BoardVo boardVo= boardService.getBoardById(b_id);
	
		//pagenation
		String pageStr = param.get("page");
		int page = pageStr == null? 1 : Integer.parseInt(pageStr);
		String pageSizeStr = param.get("pageSize");
		int pageSize = pageSizeStr == null? 10 : Integer.parseInt(pageSizeStr);	
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		paramMap.put("b_id", b_id);
		
		//답글형태 글목록
		Map<String, Object>resultMap = writeService.getWriteView(paramMap);
		
		//게시판 페이지 리스트
		List<WriteVo> writeList = (List<WriteVo>)resultMap.get("pageList");
		
		//페이지 네비게이션 문자열
		String pageNavi = (String) resultMap.get("pageNavi");
		
		model.addAttribute("b_id", b_id);
		model.addAttribute("boardName",boardVo.getB_name());
		model.addAttribute("writeList",writeList);
		model.addAttribute("pageNavi", pageNavi);

		 return "writeList";
	 }

	@RequestMapping("/writeDetail")
	 public String writeDetail(@RequestParam Map<String,String> param, Model model) {
	
		int w_id = Integer.parseInt(param.get("w_id"));
		
		WriteVo writeVo = writeService.getWriteById(w_id);
	
		List<BoardFileVo> fileList = fileService.getAllFiles(w_id);
		
		model.addAttribute("writeVo", writeVo);
		model.addAttribute("fileList", fileList);
		
		return "writeDetail";
	}
		
	
	@RequestMapping("/writeNew")
	 public String newWrite(@RequestParam Map<String,String> param, Model model) {
		int b_id = Integer.parseInt(param.get("b_id")); // 해당 게시판에 하기 위함
		model.addAttribute("b_id", b_id);
		
		if (param.get("w_parent")!=null)
		model.addAttribute("w_parent", Integer.parseInt(param.get("w_parent")));
		
		return "writeNew"; // index 페이지로
	}
	
	
	@RequestMapping("/updateIndex")
	 public String updateWrite(@RequestParam Map<String,String> param, Model model) {
	
		WriteVo writeVo = 
		writeService.getWriteById(Integer.parseInt(param.get("w_id")));
		
		model.addAttribute("b_id", writeVo.getB_id());
		model.addAttribute("writeVo", writeVo);
		
		return "writeUpdate"; //update index 페이지로
	}
	
	@RequestMapping("/writeDelete")
	 public String writeDelete(@RequestParam Map<String,String> param, 
			 				   @ModelAttribute StudentVo vo,
			 				    Model model) {

		int b_id= Integer.parseInt(param.get("b_id"));
		int w_id = Integer.parseInt(param.get("w_id"));
		String w_delny = "Y";
	
		WriteVo writeVo = new WriteVo();
		writeVo.setW_delny(w_delny);
		writeVo.setW_id(w_id);
			
		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
	
		//게시글 delete update
		writeService.deleteWrite(writeVo);
	
		
		model.addAttribute("b_id", b_id);
	
		return "redirect:writeList";
	}

	
	
	@RequestMapping("/writeCreate")
	 public String writeCreate(@RequestParam Map<String,String> param, 
			 
								 Model model,
								 BoardFileVo files,
								 MultipartHttpServletRequest request,
								 @ModelAttribute StudentVo studentVo) 
					   throws IllegalStateException, IOException{
		
		WriteVo writeVo = new WriteVo();
		
		int b_id= Integer.parseInt(param.get("b_id"));
		String content = request.getParameter("smarteditor");
	    //--답글여부 check
		if (param.get("w_parent").equals("")) {writeVo.setW_parent(0); }
		else{ writeVo.setW_parent(Integer.parseInt(param.get("w_parent"))); }
		
		writeVo.setStd_id   (studentVo.getStd_id()); 		 	 
		writeVo.setB_id	    (b_id);  			 
		writeVo.setW_title  (param.get("w_title"    ));  			 
		writeVo.setW_content(content);  	 		 
		writeVo.setW_regdt  (new Date());

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
		
		//게시글 insert
		writeService.insertWrite(writeVo);

		//파일부분---------------------------------------------------------------------------
		
		
		for (MultipartFile f: files.getFiles()) {
			logger.debug("{}","f.getOriginalFilename() : "+f.getOriginalFilename());
		}

		String path = FileUtil.fileUploadPath;

		
		for(MultipartFile f : files.getFiles()){
			if (null != f && f.getSize()> 0) {
			String fileName = UUID.randomUUID().toString();
			File uploadFile = new File(path + File.separator +fileName);
			f.transferTo(uploadFile);
			
			BoardFileVo boardFileVo = new BoardFileVo();
			boardFileVo.setW_id(writeVo.getW_id());
			System.out.println("파일업로드할때 w_id ------------------"+writeVo.getW_id());
			boardFileVo.setStd_id(studentVo.getStd_id());
			boardFileVo.setF_file(path);
			boardFileVo.setF_path(uploadFile.getAbsolutePath());
			boardFileVo.setF_name(f.getOriginalFilename());
			
			System.out.println("WriteCreateServlet boardFileVo============"+boardFileVo);
			//file insert
			fileService.insertFile(boardFileVo);
			}
		
		}
		
		try {
			request.getPart("files");
			request.getParts();
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
		model.addAttribute("b_id", b_id);
		
		//writeList 뒤에 알아서 ?를 표시해준다 신기
		return "writeList";
		//return "redirect:writeList";
	}
	
	
	
	@RequestMapping("/writeUpdate")
	 public String writeUpdate(@RequestParam Map<String,String> param, 
								 Model model,
								 BoardFileVo files,
								 MultipartHttpServletRequest request,
								 @ModelAttribute StudentVo studentVo) 
					   throws IllegalStateException, IOException{
		
		int b_id= Integer.parseInt(param.get("b_id"));
		int w_id= Integer.parseInt(param.get("w_id"));
		
		WriteVo writeVo = writeService.getWriteById(w_id);
	
		writeVo.setW_title  (param.get("w_title"    ));  			 
		writeVo.setW_content(param.get("smarteditor"));  	 		 
		writeVo.setW_id(w_id);

		System.out.println("WriteCreateServlet writeVo======>"+writeVo);
		
		//게시글 update
		writeService.updateWrite(writeVo);

		
		//파일부분---------------------------------------------------------------------------
		
		
		for (MultipartFile f: files.getFiles()) {
			logger.debug("{}","f.getOriginalFilename() : "+f.getOriginalFilename());
		}

		String path = FileUtil.fileUploadPath;

		
		for(MultipartFile f : files.getFiles()){
			if (null != f && f.getSize()> 0) {
			String fileName = UUID.randomUUID().toString();
			File uploadFile = new File(path + File.separator +fileName);
			f.transferTo(uploadFile);
			
			BoardFileVo boardFileVo = new BoardFileVo();
			boardFileVo.setW_id(writeVo.getW_id());
			System.out.println("파일업로드할때 w_id ------------------"+writeVo.getW_id());
			boardFileVo.setStd_id(studentVo.getStd_id());
			boardFileVo.setF_file(path);
			boardFileVo.setF_path(uploadFile.getAbsolutePath());
			boardFileVo.setF_name(f.getOriginalFilename());
			
			System.out.println("WriteCreateServlet boardFileVo============"+boardFileVo);
			//file insert
			fileService.insertFile(boardFileVo);
			}
		
		}
		
		try {
			request.getPart("files");
			request.getParts();
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute("b_id", b_id);
		
		//writeList 뒤에 알아서 ?를 표시해준다 
		return "redirect:writeList";
	}
	
	@RequestMapping("/fileDown")
	public String fileDown(@RequestParam("fileName") String fileName,
							@RequestParam("originalFileName") String originalFileName,
							Model model){
		model.addAttribute("fileName", fileName);
		model.addAttribute("originalFileName", originalFileName);
		return "fileDownloadView";
	}
}
