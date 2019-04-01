package kr.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;
import kr.util.AuthUtil;

public class ModifyDiaryFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("1====================");
		
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		int d_idx=Integer.parseInt(request.getParameter("d_idx"));
		DiaryDao dao=DiaryDao.getInstance();
		DiaryDto diary=dao.getDiary(d_idx);
		
		if (!AuthUtil.isAuthWriter(request, diary.getId())) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		request.setAttribute("diary", diary);
		
		return "/WEB-INF/views/diary/modifyDiaryForm.jsp";
	}

}
