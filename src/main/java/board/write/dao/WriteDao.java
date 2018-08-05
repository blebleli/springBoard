package board.write.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.mybatis.SqlMapSessionFactory;
import board.student.model.StudentVo;
import board.write.model.WriteVo;

public class WriteDao implements WriteDaoInf {

	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	@Override
	public List<WriteVo> getAllWrites(int b_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<WriteVo> writeList = session.selectList("write.getAllWrites",b_id);
		session.close();
		return writeList;
	}
	
	@Override
	public List<WriteVo> getWriteView(Map<String, Integer> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<WriteVo> writeList = session.selectList("write.getWriteView",map);
		session.close();
		return writeList;
	}
	
	

	@Override
	public WriteVo getWriteById(int w_id) {
		SqlSession session = sqlSessionFactory.openSession();
		WriteVo writeList = session.selectOne("write.getWriteById",w_id);
		session.close();
		return writeList;
	}

	@Override
	public int updateWrite(WriteVo writeVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int updateCnt = session.update("write.updateWrite",writeVo);
	    session.commit();
		session.close();
		return updateCnt;
	}


	@Override
	public int deleteWrite(WriteVo writeVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int deleteCnt = session.update("write.deleteWrite",writeVo);
	    session.commit();
		session.close();
		return deleteCnt;
	}
	

	@Override
	public int updateWriteFile(WriteVo writeVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int updateCnt = session.update("write.updateWriteFile",writeVo);
	    session.commit();
		session.close();
		return updateCnt;
	}

	
	
	@Override
	public int insertWrite(WriteVo writeVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int insertCnt = session.insert("write.insertWrite",writeVo);
	    session.commit();
		session.close();
		return insertCnt;
	}



	@Override
	public int getWriteTotCnt(int b_id) {
		SqlSession session = sqlSessionFactory.openSession();
	    int writeCnt = session.selectOne("write.getWriteTotCnt",b_id);
	    session.commit();
		session.close();
		return writeCnt;
		
	}


}
