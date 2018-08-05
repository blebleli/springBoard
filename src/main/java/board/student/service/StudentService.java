package board.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import board.student.dao.StudentDao;
import board.student.dao.StudentDaoInf;
import board.student.model.StudentVo;

@Service("studentService")
public class StudentService implements StudentServiceInf {
	
	StudentDaoInf studentDao = new StudentDao();


	@Override
	public StudentVo loginCheck(StudentVo vo) {
		return studentDao.loginCheck(vo);
	}
	

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	/**
	 * 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 9.
	 * 작성자 : pc24
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 학생 정보를 조회한다.
	 */
	@Override
	public List<StudentVo> getAllStudents() {
		return studentDao.getAllStudents();
	}

	/**
	 * 
	 * Method   : selectStudentsById 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 특정학생을 id로 조회
	 */
	@Override
	public StudentVo getStudentById(String std_id) {
		return studentDao.getStudentById(std_id);
	}

	/**
	 * 
	 * Method   : selectStudentsCount 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 특정학생을 vo로 조회
	 */
	@Override
	public StudentVo getStudentByVo(StudentVo studentVo) {
		return studentDao.getStudentByVo(studentVo);
	}

	
	
	/**
	 * 
	 * Method   : getStudentPageList 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param map
	 * @return 
	 * Method 설명 : 학생 페이지 리스트 조회
	 */
	@Override
	public Map<String, Object> getStudentPageList(Map<String, Integer> map) {
	
		//학생 페이지 리스트 조회
		List<StudentVo> studentList = studentDao.getStudentPageList(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("studentList", studentList);
		
		//전체학생건수조회
		int totCnt = studentDao.getStudentTotCnt();
		resultMap.put("totCnt", totCnt);

		//페이지 네비게이션 html 생성
		int page = map.get("page");	
		int pageSize = map.get("pageSize");
		
		resultMap.put("pageNavi", makePageNavi(page, pageSize, totCnt));

		return resultMap;
	}
	
	
	/**
	 * 
	 * Method   : makePageNavi 
	 * 최초작성일  : 2018. 7. 11. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param page
	 * @param pageSize
	 * @param totCnt
	 * @return 
	 * Method 설명 : 페이지 네비게이션 문자열 생성 
	 */
	private String makePageNavi(int page, int pageSize, int totCnt){
		int cnt = totCnt / pageSize;
		int mod = totCnt % pageSize;
		if(mod>0) cnt++;
		
		StringBuffer pageNaviStr = new StringBuffer();
		int prePage = page ==1?1:page-1;
		int nextPage = page == cnt?page:page+1;
		
		pageNaviStr.append("<li><a href=\"/student/tiles/list?page="+prePage+
				"&pageSize="+pageSize+"\" aria-label=\"Previous\">"
				+ "<span aria-hidden=\"true\">&laquo;</span></a></li>");

		
	
		for(int i=1; i<=cnt; i++){
			String activeClass = "";
			if (i==page) 
				activeClass = "class=\"active\"";

			pageNaviStr.append("<li "+activeClass+"><a href=\"/student/tiles/list?page="+i+
									 "&pageSize="+pageSize+"\">"+i+"</a></li>");
		}	
		
		
		pageNaviStr.append("<li><a href=\"/student/tiles/list?page="+nextPage+
				"&pageSize="+pageSize+ "\"aria-label=\"Next\">"
				+ "<span aria-hidden=\"true\">&raquo;</span></a></li>");
		
		return pageNaviStr.toString();
	}

	@Override
	public int studentUpdate(StudentVo studentVo) {
		// TODO Auto-generated method stub
		return studentDao.studentUpdate(studentVo);
	}



}
