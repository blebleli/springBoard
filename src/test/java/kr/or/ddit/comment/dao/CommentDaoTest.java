package kr.or.ddit.comment.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import board.comment.dao.CommentDao;
import board.comment.dao.CommentDaoInf;
import board.comment.model.CommentVo;
import board.write.dao.WriteDaoInf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								 "classpath:kr/or/ddit/config/spring/servlet-context.xml",
								 "classpath:kr/or/ddit/config/spring/datasource.xml",
								 "classpath:kr/or/ddit/config/spring/interceptor.xml"})
@WebAppConfiguration
public class CommentDaoTest {

	@Resource(name="commentDao")
	private CommentDaoInf commentDao;
	
	
	@Test
	public void getAllComments(){
		int w_id = 1;

		List<CommentVo> commentList = commentDao.getAllComments(w_id);

		for (CommentVo vo : commentList) {
			System.out.println(vo);
		}

		/***Then***/
		assertEquals(2, commentList.size());
	}


	@Test
	public void getCountComments(){
		/***Given***/
		int w_id=1;

		/***When***/
		int count = commentDao.getCountComments(w_id);

		/***Then***/
		assertEquals(2, count);
	}
	
	@Test
	public void getCommentById(){
		/***Given***/
		int c_id=1;

		/***When***/
		CommentVo commentVo =commentDao.getCommentById(c_id);

		/***Then***/
		assertEquals(1, commentVo.getW_id());
	}
	
	@Test
	public void updateComment (){
		/***Given***/
		CommentVo commentVo = new CommentVo();
		commentVo.setC_delny("N");
		commentVo.setC_id(1);
		commentVo.setC_cmt("수정된 댓글");
		/***When***/
		int update = commentDao.updateComment(commentVo);

		/***Then***/
		assertEquals(1, update);

	}
	
	//@Test
	public void insertComment (){

		/***Given***/
		CommentVo commentVo = new CommentVo();
		commentVo.setW_id(1);
		commentVo.setStd_id("std_25");
		commentVo.setC_cmt("수정된 댓글");
		commentVo.setC_delny("N");
		commentVo.setC_regdt(new Date());
		/***When***/
		int create = commentDao.updateComment(commentVo);

		/***Then***/
		assertEquals(1, create);

	}
}
