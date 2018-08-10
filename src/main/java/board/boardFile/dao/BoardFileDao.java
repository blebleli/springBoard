
package board.boardFile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import board.boardFile.model.BoardFileVo;
import board.mybatis.SqlMapSessionFactory;

@Repository("boardFileDao")
public class BoardFileDao implements BoardFileDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<BoardFileVo> getAllFiles(int w_id) {
		
		return session.selectList("boardFile.getAllFiles",w_id);
	
	}

	@Override
	public int countFile(int w_id) {

		return session.selectOne("boardFile.countFile",w_id);

	}

	@Override
	public BoardFileVo getFileById(int f_id) {
		
		return session.selectOne("boardFile.getFileById",f_id);

	}

	@Override
	public int updateFile(BoardFileVo boardFileVo) {

		return session.update("boardFile.updateFile",boardFileVo);
	   
	
	}

	@Override
	public int insertFile(BoardFileVo boardFileVo) {
	
		return session.insert("boardFile.insertFile",boardFileVo);
	   
	}


}
