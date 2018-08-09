package board.boardFile.dao;

import java.util.List;

import board.boardFile.model.BoardFileVo;

public interface BoardFileDaoInf {
	/**
	 * 
	 * Method   : getAllFiles 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 글번호로 모든 파일을 가져온다
	 */
	List<BoardFileVo> getAllFiles(int w_id);

	
	/**
	 * 
	 * Method   : countFile 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 글번호로 파일개수를 가져온다
	 */
	int countFile(int w_id);
	
	
	
	/**
	 * 
	 * Method   : getFileById 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param b_id
	 * @return 
	 * Method 설명 : 파일id로 파일을 가져온다
	 */
	BoardFileVo getFileById(int b_id);

	/**
	 * 
	 * Method   : updateFile 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param boardVo
	 * @return 
	 * Method 설명 : 파일 수정
	 */
	int updateFile(BoardFileVo boardFileVo);


	/**
	 * 
	 * Method   : insertFile 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param boardVo
	 * @return 
	 * Method 설명 : 파일추가 생성
	 */
	public int insertFile(BoardFileVo boardFileVo);
}
