package board.student.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import board.mybatis.SqlMapSessionFactory;
import board.student.model.StudentVo;

public class StudentDao implements StudentDaoInf {
	

	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	

	@Override
	public StudentVo loginCheck(StudentVo vo) {
		SqlSession session = sqlSessionFactory.openSession();
		StudentVo studentVo = session.selectOne("student.loginCheck",vo);
		session.close();
		return studentVo;
		
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		
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
		SqlSession session = sqlSessionFactory.openSession();
		
		List<StudentVo> studentList = session.selectList("student.getAllStudents");
		session.close();
		
		return studentList;
	}
	
	/**
	 * 
	 * Method   : selectStudentsById 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param id
	 * @return 
	 * Method 설명 : 학생 정보를 id로 조회
	 */

	@Override
	public StudentVo getStudentById(String stdId) {
		SqlSession session = sqlSessionFactory.openSession();
		StudentVo studentList = session.selectOne("student.getStudentById",stdId);
		//단수형태의 값을 리턴 selectOne
		session.close();
		return studentList;
	}
	
	
	
	
	@Override
	public StudentVo getStudentByVo(StudentVo studentVo) {
		SqlSession session = sqlSessionFactory.openSession();
		
		StudentVo vo = session.selectOne("student.getStudentByVo",studentVo);
		//단수형태의 값을 리턴 selectOne
		session.close();
		
		return vo;
	
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
	public List<StudentVo> getStudentPageList(Map<String, Integer> map) {
		SqlSession session = sqlSessionFactory.openSession();
		
		List<StudentVo> studentList = session.selectList("student.getStudentPageList",map);
		
		session.close();
		return studentList;
	}
	
	@Override
	public int getStudentTotCnt() {
		SqlSession session = sqlSessionFactory.openSession();
		
	    int studentCnt = session.selectOne("student.getStudentTotCnt");
	    session.commit();
		session.close();
		return studentCnt;
	}

	
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
	@Override
	public int studentUpdate(StudentVo studentVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int updateCnt = session.update("student.studentUpdate",studentVo);
	    session.commit();
		session.close();
		return updateCnt;
	}


}
