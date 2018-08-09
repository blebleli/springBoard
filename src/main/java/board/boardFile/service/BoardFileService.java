package board.boardFile.service;

import java.util.List;

import board.boardFile.dao.BoardFileDao;
import board.boardFile.model.BoardFileVo;

public class BoardFileService implements BoardFileServiceInf {

	BoardFileDao boardFileDao = new BoardFileDao();
	
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
