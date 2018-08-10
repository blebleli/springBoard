package board.write.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import board.mybatis.SqlMapSessionFactory;
import board.student.model.StudentVo;
import board.write.model.WriteVo;

@Repository("writeDao")
public class WriteDao implements WriteDaoInf {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<WriteVo> getAllWrites(int b_id) {
		
		return session.selectList("write.getAllWrites",b_id);
	
	}
	
	@Override
	public List<WriteVo> getWriteView(Map<String, Integer> map) {
		
		return session.selectList("write.getWriteView",map);
	
	}
	
	

	@Override
	public WriteVo getWriteById(int w_id) {
		
		return session.selectOne("write.getWriteById",w_id);
	
	}

	@Override
	public int updateWrite(WriteVo writeVo) {
			
		return session.update("write.updateWrite",writeVo);

	}


	@Override
	public int deleteWrite(WriteVo writeVo) {
			
		return session.update("write.deleteWrite",writeVo);
	  
	}
	

	@Override
	public int updateWriteFile(WriteVo writeVo) {
			
		return session.update("write.updateWriteFile",writeVo);
	 
	}

	
	
	@Override
	public int insertWrite(WriteVo writeVo) {
			
		return session.insert("write.insertWrite",writeVo);
	
	}



	@Override
	public int getWriteTotCnt(int b_id) {
		
		return session.selectOne("write.getWriteTotCnt",b_id);
	
		
	}


}
