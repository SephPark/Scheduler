package kr.diary.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;

public class WriteDiaryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String>mapAjax=new HashMap<String, String>();
		HttpSession session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		DiaryDao dao=DiaryDao.getInstance();
		
		DiaryDto diary=new DiaryDto();
		diary.setD_title(request.getParameter("d_title"));
		diary.setD_content(request.getParameter("d_content"));
		diary.setId(user_id);
		
		dao.writeDiary(diary);
		
		//return "/WEB-INF/views/diary/listDiary.jsp";
		return "redirect:/diary/listDiaryForm.do";
	}

}
