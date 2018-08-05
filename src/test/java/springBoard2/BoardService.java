package springBoard2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import board.student.model.StudentVo;
import board.student.service.StudentService;
import board.student.service.StudentServiceInf;

public class BoardService {

	private StudentServiceInf studentService;
	
	//@before - test - after
	@Before
	public void setup(){
		studentService = new StudentService();
	}
	
	
	@Test
	public void selectAllStudents() {

		/***Given***/

		/***When***/
		List<StudentVo> stdList = studentService.getAllStudents();
		for(StudentVo vo : stdList){
			System.out.println(vo);
		}
		
		/***Then***/
		assertEquals(25, stdList.size());
	}
}
