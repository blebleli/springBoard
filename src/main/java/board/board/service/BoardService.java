package board.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import board.board.dao.BoardDaoInf;
import board.board.model.BoardVo;

@Service("boardService")
public class BoardService implements BoardServiceInf{

	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Override
	public List<BoardVo> getAllBoards() {
		// TODO Auto-generated method stub
		return boardDao.getAllBoards();
	}

	@Override
	public BoardVo getBoardById(int b_id) {
		// TODO Auto-generated method stub
		return boardDao.getBoardById(b_id);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return boardDao.insertBoard(boardVo);
	}

}
