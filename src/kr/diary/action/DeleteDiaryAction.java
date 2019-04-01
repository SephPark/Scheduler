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
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		
		int d_idx=Integer.parseInt(request.getParameter("d_idx"));
		DiaryDao dao=DiaryDao.getInstance();
		DiaryDto diary=dao.getDiary(d_idx);
		
		//�α����� ID�� �ۼ��� ID ��ġ ����
		if(!AuthUtil.isAuthWriter(request, diary.getId())) {
			//�α����� ID�� �ۼ��� ID ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		dao.deleteDiary(d_idx);
		
		return "redirect:/diary/listDiaryForm.do";
	}

}
