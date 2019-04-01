package kr.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;
import kr.util.AuthUtil;

public class DeleteDiaryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		int d_idx=Integer.parseInt(request.getParameter("d_idx"));
		DiaryDao dao=DiaryDao.getInstance();
		DiaryDto diary=dao.getDiary(d_idx);
		
		//로그인한 ID와 작성자 ID 일치 여부
		if(!AuthUtil.isAuthWriter(request, diary.getId())) {
			//로그인한 ID와 작성자 ID 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		dao.deleteDiary(d_idx);
		
		return "redirect:/diary/listDiaryForm.do";
	}

}
