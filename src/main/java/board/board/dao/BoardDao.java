package board.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import board.board.model.BoardVo;

@Repository("boardDao")
public class BoardDao implements BoardDaoInf {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<BoardVo> getAllBoards() {
		return session.selectList("board.getAllBoards");
	}

	@Override
	public BoardVo getBoardById(int b_id) {
		return session.selectOne("board.getBoardById",b_id);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return session.update("board.updateBoard",boardVo);
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return session.insert("board.insertBoard",boardVo);
	}
}
