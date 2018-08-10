package board.student.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import board.mybatis.SqlMapSessionFactory;
import board.student.model.StudentVo;

@Repository("studentDao")
public class StudentDao implements StudentDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	

	@Override
	public StudentVo loginCheck(StudentVo vo) {
	
		return session.selectOne("student.loginCheck",vo);
	
	
		
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
		
		
		return session.selectList("student.getAllStudents");
	
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
		
		return session.selectOne("student.getStudentById",stdId);
		//단수형태의 값을 리턴 selectOne

	}
	
	
	
	
	@Override
	public StudentVo getStudentByVo(StudentVo studentVo) {
		
		
		return session.selectOne("student.getStudentByVo",studentVo);

	
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
		
		
		return session.selectList("student.getStudentPageList",map);

	}
	
	@Override
	public int getStudentTotCnt() {
		
		
		return session.selectOne("student.getStudentTotCnt");
	
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
			
		return session.update("student.studentUpdate",studentVo);

	}


}
