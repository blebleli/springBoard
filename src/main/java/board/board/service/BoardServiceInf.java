package board.board.service;

import java.util.List;

import board.board.model.BoardVo;

public interface BoardServiceInf {
	/**
	 * 
	 * Method   : getAllBoards 
	 * 최초작성일  : 2018. 7. 19. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 모든 게시판 를 list 형태로 반환
	 */
		List<BoardVo> getAllBoards();
		
		
	/**
	 * 
	 * Method   : getBoardById 
	 * 최초작성일  : 2018. 7. 19. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param b_id
	 * @return 
	 * Method 설명 : 게시판 id로 게시판조회
	 */
		BoardVo getBoardById(int b_id);
		

	/**
	 * 
	 * Method   : updateBoard 
	 * 최초작성일  : 2018. 7. 19. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param createBoardVo
	 * @return 
	 * Method 설명 : 게시판 정보 업데이트
	 */
		int updateBoard (BoardVo boardVo);
		
		
	/**
	 * 
	 * Method   : insertBoard 
	 * 최초작성일  : 2018. 7. 19. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param boardVo
	 * @return 
	 * Method 설명 : 게시판 추가 메서드
	 */
		public int insertBoard(BoardVo boardVo);		
	
}
