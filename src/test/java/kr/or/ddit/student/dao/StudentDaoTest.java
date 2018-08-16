package kr.or.ddit.student.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import board.comment.dao.CommentDaoInf;
import board.student.dao.StudentDao;
import board.student.dao.StudentDaoInf;
import board.student.model.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class StudentDaoTest {

	

	@Resource(name="studentDao")
	private StudentDaoInf studentDao;
	

	/**
	 * 
	 * Method   : selectAllStudentsTest 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 :  
	 * Method 설명 : 전체 학생 정보를 조회한다.
	 */
	@Test
	public void selectAllStudentsTest() {
		/***Given***/

		/***When***/
		List<StudentVo> studentList = studentDao.getAllStudents();
		
		for (StudentVo vo : studentList) {
			System.out.println(vo);
		}
		/***Then***/
		assertEquals(25, studentList.size());
	}
	
	/**
	 * 
	 * Method   : selectStudentsById 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 :  
	 * Method 설명 : 학생정보조회 test
	 */ 
	@Test
	public void selectStudentsById() {
		/***Given***/
		String std_id = "std_1";
		
		/***When***/
		StudentVo student = studentDao.getStudentById(std_id);
		System.out.println("selectStudentsById : "+ student);
		
		/***Then***/
		assertEquals("김마음", student.getName());
	}
	
	
	/**
	 * 
	 * Method   : selectStudentsById 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 :  
	 * Method 설명 : 학생정보조회 test
	 */ 
	@Test
	public void selectStudentsByVo() {
		/***Given***/
		StudentVo studentVo = new StudentVo();
		String std_id = "std_1";
		studentVo.setStd_id(std_id);
		
		/***When***/
		StudentVo student = studentDao.getStudentByVo(studentVo);

		System.out.println("selectStudentsByVo : "+ student);
		
		/***Then***/
		assertEquals("김마음", student.getName());
	}
	

	@Test
	public void getStudentPageList() {
		/***Given***/
		//StudentVo paramVo = new StudentVo();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", 3);
		map.put("pageSize", 10);
		
		/***When***/
		List<StudentVo> student = studentDao.getStudentPageList(map);
		
		for (StudentVo vo : student) {
			System.out.println("pageSize : "+vo);
		}
		
		/***Then***/
		assertEquals(5, student.size());
	}
	
	@Test
	public void getStudentTotCnt() {
		/***Given***/

		/***When***/
		int studentCnt = studentDao.getStudentTotCnt();
		
		/***Then***/
		assertEquals(25,studentCnt );
	}
	
}
