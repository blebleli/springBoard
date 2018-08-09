
package board.boardFile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.boardFile.model.BoardFileVo;
import board.mybatis.SqlMapSessionFactory;

public class BoardFileDao implements BoardFileDaoInf {
	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();

	@Override
	public List<BoardFileVo> getAllFiles(int w_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<BoardFileVo> fileList = session.selectList("boardFile.getAllFiles",w_id);
		session.close();
		return fileList;
	}

	@Override
	public int countFile(int w_id) {
		SqlSession session = sqlSessionFactory.openSession();
		int fileCnt = session.selectOne("boardFile.countFile",w_id);
		session.close();
		return fileCnt;
	}

	@Override
	public BoardFileVo getFileById(int f_id) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardFileVo boardFileVo = session.selectOne("boardFile.getFileById",f_id);
		session.close();
		return boardFileVo;
	}

	@Override
	public int updateFile(BoardFileVo boardFileVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int updateCnt = session.update("boardFile.updateFile",boardFileVo);
	    session.commit();
		session.close();
		return updateCnt;
	}

	@Override
	public int insertFile(BoardFileVo boardFileVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int insertCnt = session.insert("boardFile.insertFile",boardFileVo);
	    session.commit();
		session.close();
		return insertCnt;
	}



}
