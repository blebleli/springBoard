package board.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.comment.model.CommentVo;
import board.mybatis.SqlMapSessionFactory;

public class CommentDao implements CommentDaoInf {

	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();

	@Override
	public List<CommentVo> getAllComments(int w_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<CommentVo> commentList = session.selectList("comment.getAllComments",w_id);
		session.close();
		return commentList;
	}

	@Override
	public int getCountComments(int w_id) {
		SqlSession session = sqlSessionFactory.openSession();
		int commentCnt = session.selectOne("comment.getCountComments",w_id);
		session.close();
		return commentCnt;
	}
	
	@Override
	public CommentVo getCommentById(int c_id) {
		SqlSession session = sqlSessionFactory.openSession();
		CommentVo commentVo = session.selectOne("comment.getCommentById",c_id);
		session.close();
		return commentVo;
	}

	@Override
	public int updateComment(CommentVo commentVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int updateCnt = session.update("comment.updateComment",commentVo);
	    session.commit();
		session.close();
		return updateCnt;
	}

	@Override
	public int insertComment(CommentVo commentVo) {
		SqlSession session = sqlSessionFactory.openSession();	
	    int insertCnt = session.insert("comment.insertComment",commentVo);
	    session.commit();
		session.close();
		return insertCnt;
	}


}
