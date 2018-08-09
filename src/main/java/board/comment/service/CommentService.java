package board.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import board.comment.dao.CommentDao;
import board.comment.model.CommentVo;

@Service("commentService")
public class CommentService implements CommentServiceInf {

	CommentDao commentDao = new CommentDao();
	
	
	@Override
	public List<CommentVo> getAllComments(int w_id) {
		// TODO Auto-generated method stub
		return commentDao.getAllComments(w_id);
	}
	
	@Override
	public int getCountComments(int w_id) {
		// TODO Auto-generated method stub
		return commentDao.getCountComments(w_id);
	}

	@Override
	public CommentVo getCommentById(int c_id) {
		// TODO Auto-generated method stub
		return commentDao.getCommentById(c_id);
	}

	@Override
	public int updateComment(CommentVo commentVo) {
		// TODO Auto-generated method stub
		return commentDao.updateComment(commentVo);
	}

	@Override
	public int insertComment(CommentVo commentVo) {
		// TODO Auto-generated method stub
		return commentDao.insertComment(commentVo);
	}


}
