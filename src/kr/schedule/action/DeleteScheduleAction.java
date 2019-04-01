package kr.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;
import kr.util.AuthUtil;

public class DeleteScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		int sc_idx=Integer.parseInt(request.getParameter("sc_idx"));
		ScheduleDao dao=ScheduleDao.getInstance();
		ScheduleDto schedule=dao.getSchedule(sc_idx);
		
		//로그인한 ID와 작성자 ID 일치 여부
		if(!AuthUtil.isAuthWriter(request, schedule.getId())) {
			//로그인한 ID와 작성자 ID 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		dao.deleteSchedule(sc_idx);
		
		return "redirect:/main/main.do";
	}

}
