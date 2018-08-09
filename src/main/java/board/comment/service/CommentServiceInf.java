package board.comment.service;

import java.util.List;

import board.comment.model.CommentVo;

public interface CommentServiceInf {
	/**
	 * 
	 * Method   : getAllComments 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 글번호로 모든 코멘트를 가져온다
	 */
	List<CommentVo> getAllComments(int w_id);

	
	/**
	 * 
	 * Method   : getAllComments 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 글번호로 모든 코멘트 개수를 가져온다
	 */
	int getCountComments(int w_id);
	

	/**
	 * 
	 * Method   : getCommentById 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param b_id
	 * @return 
	 * Method 설명 : 코멘트id로 코멘트를 가져온다
	 */
	CommentVo getCommentById(int c_id);

	/**
	 * 
	 * Method   : updateComment 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param boardVo
	 * @return 
	 * Method 설명 : 코멘트 수정
	 */
	int updateComment (CommentVo commentVo);


	/**
	 * 
	 * Method   : insertComment 
	 * 최초작성일  : 2018. 7. 23. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param boardVo
	 * @return 
	 * Method 설명 : 코멘트 생성
	 */
	public int insertComment (CommentVo commentVo);
}
