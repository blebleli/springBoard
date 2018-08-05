package board.student.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import board.student.model.StudentVo;
import board.student.service.StudentServiceInf;

@RequestMapping("/student")
@Controller("studentController")
public class StudentController {
	
	//학생 리스트 처리
	@Resource(name="studentService")
	private StudentServiceInf studentService;
	
	//page, pageSize parameter 확인
	
	@RequestMapping("/listTest")
	public String listTest(Model model) {
		
		List<StudentVo> studentVo = studentService.getAllStudents();
		
		model.addAttribute("studentVo",studentVo); 
		
		return "student/listTest";
	}
	
	
	@RequestMapping("/list")
	public String studentList(@RequestParam(value="page", defaultValue="1")int page, 
							  @RequestParam(value="pageSize", defaultValue="10") int pageSize,
							  Model model){
//	@RequestMapping("/list")
//	public String studentList(@RequestParam Map<String, Integer> map, Model model){
	
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		
		Map<String, Object> resultMap = studentService.getStudentPageList(paramMap);
		
		model.addAllAttributes(resultMap); 
		
		return "student/list";
	}
	
	//학생 상세 정보 조회
	@RequestMapping("/detail")
	public String detail(StudentVo vo, Model model){ //id값이 설정된 상태로 오게된다.
		
		StudentVo studentVo = studentService.getStudentByVo(vo);
		
		model.addAttribute("studentVo",studentVo);

		return"student/detail";
	}
	
	
	
	
	@RequestMapping("/tiles/list")
	public String tilesStudentList(@RequestParam(value="page", defaultValue="1")int page, 
			  @RequestParam(value="pageSize", defaultValue="10") int pageSize,
			  Model model){
		//@RequestMapping("/list")
		//public String studentList(@RequestParam Map<String, Integer> map, Model model){
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);

		Map<String, Object> resultMap = studentService.getStudentPageList(paramMap);
		
		model.addAllAttributes(resultMap); 
			
		return"studentList";
	}
	
	@RequestMapping("/tiles/detail")
	public String tilesStudentdetail(StudentVo vo, Model model){ //id값이 설정된 상태로 오게된다.
		
		StudentVo studentVo = studentService.getStudentByVo(vo);
		
		model.addAttribute("studentVo",studentVo);

		return"studentDetail";
	}
	
	
	
	//ajax 요청을 위한 jsno 응답
	@RequestMapping("/list/json")
	@ResponseBody  //선생님껀느 string objext 타입
	public Map<String, Object>  studentJsonList(@RequestParam(value="page", defaultValue="1")int page, 
							  @RequestParam(value="pageSize", defaultValue="10") int pageSize,
							  Model model){
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		
		Map<String, Object> resultMap = studentService.getStudentPageList(paramMap);
		
		return resultMap; 
	}
	
	
	
}
