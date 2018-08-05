package board.write.service;

import java.util.List;
import java.util.Map;

import board.write.model.WriteVo;

public interface WriteServiceInf {
	/**
	 * 
	 * Method   : getAllBoards 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 :
	 */
	List<WriteVo> getAllWrites(int b_id);

	
	/**
	 * 
	 * Method   : getWriteView 
	 * 최초작성일  : 2018. 7. 21. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판아이디로 게시판형태의 뷰
	 */
	Map<String, Object> getWriteView(Map<String, Integer> map);


	/**
	 * 
	 * Method   : getBoardById 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param b_id
	 * @return 
	 * Method 설명 :
	 */
	WriteVo getWriteById(int w_id);


	/**
	 * 
	 * Method   : updateBoard 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param writeVo
	 * @return 
	 * Method 설명 :
	 */
	int updateWrite (WriteVo writeVo);

	/**
	 * 
	 * Method   : deleteWrite 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param writeVo
	 * @return 
	 * Method 설명 : 게시글 수정
	 */
	int deleteWrite (WriteVo writeVo);


	/**
	 * 
	 * Method   : deleteWrite 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param writeVo
	 * @return 
	 * Method 설명 : 게시글 수정
	 */
	int updateWriteFile (WriteVo writeVo);
	
	
	
	/**
	 * 
	 * Method   : insertBoard 
	 * 최초작성일  : 2018. 7. 20. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param writeVo
	 * @return 
	 * Method 설명 :
	 */
	public int insertWrite(WriteVo writeVo);


	/**
	 * 
	 * Method   : getStudentTotCnt 
	 * 최초작성일  : 2018. 7. 10. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param map
	 * @return 
	 * Method 설명 : 학생수
	 */
	int getWriteTotCnt(int b_id);

}
