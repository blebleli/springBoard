package board.write.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import board.student.dao.StudentDao;
import board.student.dao.StudentDaoInf;
import board.student.model.StudentVo;
import board.write.dao.WriteDao;
import board.write.dao.WriteDaoInf;
import board.write.model.WriteVo;

@Service("writeService")
public class WriteService implements WriteServiceInf {

	WriteDaoInf	writeDao = new WriteDao();
	
	@Override
	public List<WriteVo> getAllWrites(int b_id) {
		// TODO Auto-generated method stub
		return writeDao.getAllWrites(b_id);
	}
	
	@Override
	public Map<String, Object> getWriteView(Map<String, Integer> map) {
		// 페이지 리스트 조회
		List<WriteVo> pageList = writeDao.getWriteView(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageList", pageList);
		
		//전체건수조회
		int totCnt = writeDao.getWriteTotCnt(map.get("b_id"));
		resultMap.put("totCnt", totCnt);

		//페이지 네비게이션 html 생성
		int page = map.get("page");	
		int pageSize = map.get("pageSize");
		int b_id=map.get("b_id");
				
		resultMap.put("pageNavi", makePageNavi(page, pageSize, totCnt, b_id));

		return resultMap;
	}

	@Override
	public WriteVo getWriteById(int w_id) {
		// TODO Auto-generated method stub
		return writeDao.getWriteById(w_id);
	}

	@Override
	public int updateWrite(WriteVo writeVo) {
		// TODO Auto-generated method stub
		return writeDao.updateWrite(writeVo);
	}
	

	@Override
	public int deleteWrite(WriteVo writeVo) {
		// TODO Auto-generated method stub
		return writeDao.deleteWrite(writeVo);
	}
	
	
	@Override
	public int updateWriteFile(WriteVo writeVo) {
		// TODO Auto-generated method stub
		return writeDao.updateWriteFile(writeVo);
	}


	@Override
	public int insertWrite(WriteVo writeVo) {
		// TODO Auto-generated method stub
		return writeDao.insertWrite(writeVo);
	}

	
	@Override
	public int getWriteTotCnt(int b_id) {
		// TODO Auto-generated method stub
		return writeDao.getWriteTotCnt(b_id);
	}
	
	/**
	 * 
	 * Method   : makePageNavi 
	 * 최초작성일  : 2018. 7. 11. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param page
	 * @param pageSize
	 * @param totCnt
	 * @return 
	 * Method 설명 : 페이지 네비게이션 문자열 생성 
	 */
	private String makePageNavi(int page, int pageSize, int totCnt, int b_id){
		int cnt = totCnt / pageSize;
		int mod = totCnt % pageSize;
		if(mod>0) cnt++;
		
		StringBuffer pageNaviStr = new StringBuffer();
		int prePage = page ==1?1:page-1;
		int nextPage = page == cnt?page:page+1;
		int filstPage = 1;
	
		
		pageNaviStr.append("<li><a href=\"/write/writeList?b_id="+b_id+"&page="+filstPage+
				"&pageSize="+pageSize+"\" aria-label=\"Previous\">"
				+ "<span aria-hidden=\"true\">&laquo&laquo;</span></a></li>");
		pageNaviStr.append("<li><a href=\"/write/writeList?b_id="+b_id+"&page="+prePage+
				"&pageSize="+pageSize+"\" aria-label=\"Previous\">"
				+ "<span aria-hidden=\"true\">&laquo;</span></a></li>");

		for(int i=1; i<=cnt; i++){
			String activeClass = "";
			if (i==page) 
				activeClass = "class=\"active\"";

			pageNaviStr.append("<li "+activeClass+"><a href=\"/write/writeList?b_id="+b_id+"&page="+i+
									 "&pageSize="+pageSize+"\">"+i+"</a></li>");
		}	
		
		pageNaviStr.append("<li><a href=\"/write/writeList?b_id="+b_id+"&page="+nextPage+
				"&pageSize="+pageSize+ "\"aria-label=\"Next\">"
				+ "<span aria-hidden=\"true\">&raquo;</span></a></li>");
		pageNaviStr.append("<li><a href=\"/write/writeList?b_id="+b_id+"&page="+cnt+
				"&pageSize="+pageSize+ "\"aria-label=\"Next\">"
				+ "<span aria-hidden=\"true\">&raquo&raquo;</span></a></li>");
		
		return pageNaviStr.toString();
	}


}
