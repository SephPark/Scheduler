package kr.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;
import kr.util.AuthUtil;
import kr.util.StringUtil;

public class DetailDiaryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		DiaryDao dao=DiaryDao.getInstance();
		
		int d_idx=Integer.parseInt(request.getParameter("d_idx"));
		
				
		dao.getDiary(d_idx);
		
		System.out.println(d_idx);
		
		DiaryDto diary=dao.getDiary(d_idx);
		
		//diary.setD_title(StringUtil.useNoHtml(diary.getD_title()));
		//diary.setD_content(StringUtil.useBrNoHtml(diary.getD_content()));
		
		request.setAttribute("diary", diary);
		
		return "/WEB-INF/views/diary/detailDiary.jsp";
	}

}
