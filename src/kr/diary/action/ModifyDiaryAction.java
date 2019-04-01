package kr.diary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.diary.dao.DiaryDao;
import kr.diary.domain.DiaryDto;
import kr.util.AuthUtil;

public class ModifyDiaryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("===modify Action=====");
		
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//
	
		int d_idx=Integer.parseInt(request.getParameter("d_idx"));
		
		System.out.println(d_idx);
		
		DiaryDao dao=DiaryDao.getInstance();
		//db�� �ִ� ���̾
		DiaryDto dbDiary=dao.getDiary(d_idx);
		
		if(!AuthUtil.isAuthWriter(request, dbDiary.getId())) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//���� �־��� ���̾
		DiaryDto diary=new DiaryDto();
		
		diary.setD_idx(d_idx);
		diary.setD_title(request.getParameter("d_title"));
		diary.setD_content(request.getParameter("d_content"));
		
		dao.modifyDiary(diary);
		
		return "redirect:/diary/listDiaryForm.do";
	}

}
