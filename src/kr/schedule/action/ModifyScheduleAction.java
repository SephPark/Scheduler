package kr.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.AlarmDto;
import kr.schedule.domain.ScheduleDto;
import kr.schedule.domain.ScheduleInvitedUserDto;
import kr.util.AuthUtil;

public class ModifyScheduleAction implements Action {

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
		
		schedule.setSc_title(request.getParameter("sc_title"));
		schedule.setId(AuthUtil.getUser_id(request));
		schedule.setSc_place(request.getParameter("sc_place"));
		schedule.setSc_start_date(request.getParameter("sc_start_date"));
		schedule.setSc_start_time(request.getParameter("sc_start_time"));
		schedule.setSc_end_date(request.getParameter("sc_end_date"));
		schedule.setSc_end_time(request.getParameter("sc_end_time"));
		schedule.setSc_content(request.getParameter("sc_content"));
		//schedule.setSc_all_day(request.getParameter("allDay"));
		//폼에서 checked가 넘어오면 여기서 y로 반환?
		
		if (request.getParameter("sc_all_day")==null) {
			schedule.setSc_all_day("N");
		}else {
			schedule.setSc_all_day("Y");
		}
		
		
		dao.modifySchedule(schedule);
		
		return "/WEB-INF/views/schedule/modifySchedule.jsp";
	}

}
