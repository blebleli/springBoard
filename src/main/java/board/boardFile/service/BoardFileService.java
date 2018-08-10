package board.boardFile.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import board.boardFile.dao.BoardFileDao;
import board.boardFile.dao.BoardFileDaoInf;
import board.boardFile.model.BoardFileVo;
import board.student.dao.StudentDaoInf;

@Service("fileService")
public class BoardFileService implements BoardFileServiceInf {


	@Resource(name="boardFileDao")
	private BoardFileDaoInf boardFileDao;
	
	
	@Override
	public List<BoardFileVo> getAllFiles(int w_id) {
		// TODO Auto-generated method stub
		return boardFileDao.getAllFiles(w_id);
	}

	@Override
	public int countFile(int w_id) {
		// TODO Auto-generated method stub
		return boardFileDao.countFile(w_id);
	}

	@Override
	public BoardFileVo getFileById(int f_id) {
		// TODO Auto-generated method stub
		return boardFileDao.getFileById(f_id);
	}

	@Override
	public int updateFile(BoardFileVo boardFileVo) {
		// TODO Auto-generated method stub
		return boardFileDao.updateFile(boardFileVo);
	}

	@Override
	public int insertFile(BoardFileVo boardFileVo) {
		// TODO Auto-generated method stub
		return boardFileDao.insertFile(boardFileVo);
	}

}
