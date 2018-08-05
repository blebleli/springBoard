package board.student.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import board.student.model.StudentVo;

public interface StudentServiceInf {
	
	/**
	 * 
	 * Method : loginCheck
	 * 최초작성일 : 2018. 8. 5.
	 * 작성자 : pc24
	 * 변경이력 :
	 * @return
	 * Method 설명 : 로그인 체크
	 */
	public StudentVo loginCheck(StudentVo vo);	
	
	/**
	 * 
	 * Method : loginCheck
	 * 최초작성일 : 2018. 8. 5.
	 * 작성자 : pc24
	 * 변경이력 :
	 * @return
	 * Method 설명 : 로그아웃 체크
	 */
	public void logout(HttpSession session);	
	
	
	
	
	/**
	 * 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 9.
	 * 작성자 : pc24
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 학생 정보를 조회한다.
	 */
	List<StudentVo> getAllStudents();
	
	
	/**
	 * 
	 * Method   : selectStudentsById 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 특정학생을 id로 조회
	 */
	StudentVo getStudentById(String std_id);
	
	/**
	 * 
	 * Method   : selectStudentsCount 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 특정학생을 vo로 조회
	 */
	StudentVo getStudentByVo(StudentVo studentVo);
	
	
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
	Map<String, Object> getStudentPageList(Map<String, Integer> map);
	
	
	/**
	 * 
	 * Method   : studentUpdate 
	 * 최초작성일  : 2018. 7. 17. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param studentVo
	 * @return 
	 * Method 설명 : 학생정보 업데이트
	 */
	int studentUpdate(StudentVo studentVo);

}



