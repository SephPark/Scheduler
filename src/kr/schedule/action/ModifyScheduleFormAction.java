package kr.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;
import kr.util.AuthUtil;

public class ModifyScheduleFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		int sc_idx=Integer.parseInt(request.getParameter("sc_idx"));
		System.out.println(sc_idx);
		
		ScheduleDao dao=ScheduleDao.getInstance();
		ScheduleDto schedule=dao.getSchedule(sc_idx);
		
		if (!AuthUtil.isAuthWriter(request, schedule.getId())) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		request.setAttribute("schedule", schedule);

		return "/WEB-INF/views/schedule/modifyScheduleForm.jsp";

	}

}
