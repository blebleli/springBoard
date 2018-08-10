package board.comment.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import board.comment.model.CommentVo;
@Repository("commentDao")
public class CommentDao implements CommentDaoInf {


	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<CommentVo> getAllComments(int w_id) {
		
		return session.selectList("comment.getAllComments",w_id);
		
	}

	@Override
	public int getCountComments(int w_id) {
		
		return session.selectOne("comment.getCountComments",w_id);
	
	}
	
	@Override
	public CommentVo getCommentById(int c_id) {
		
		return session.selectOne("comment.getCommentById",c_id);
	
	}

	@Override
	public int updateComment(CommentVo commentVo) {
			
		return session.update("comment.updateComment",commentVo);

	}

	@Override
	public int insertComment(CommentVo commentVo) {
			
		return session.insert("comment.insertComment",commentVo);

	}


}
