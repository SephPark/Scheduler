package kr.diary.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;
import kr.util.AuthUtil;
import kr.util.PagingUtil;

public class ListDiaryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		 * String id=AuthUtil.getUser_id(request); String
		 * pageNum=request.getParameter("pageNum"); if(pageNum==null) pageNum = "1";
		 * 
		 * int rowCount = 20; //한 페이지의 게시물 수 int pageCount = 10; //한 화면의 페이지 수 int
		 * currentPage = Integer.parseInt(pageNum);
		 * 
		 * DiaryDao dao=DiaryDao.getInstance(); int count=dao.getDiaryCount(id);
		 * 
		 * PagingUtil page=new PagingUtil(currentPage, count, rowCount, pageCount,
		 * "listDiary.do");
		 * 
		 * List<DiaryDto> list=null; if (count>0) {
		 * list=dao.getDiaryList(page.getStartCount(),page.getEndCount(),id); }
		 * 
		 * request.setAttribute("count", count); request.setAttribute("list", list);
		 * request.setAttribute("pagingHtml", page.getPagingHtml());
		 */
		
		String id=AuthUtil.getUser_id(request);
		DiaryDao dao=DiaryDao.getInstance();
		
		List<DiaryDto> list=null;
		list=dao.getDiary(id);
		
		HashMap<String, Object> mapAjax=new HashMap<String,Object>();
		
		mapAjax.put("list", list);
		
		ObjectMapper mapper=new ObjectMapper();
		
		String jsonData=mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}
