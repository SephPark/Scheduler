package kr.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;

public class DetailScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int sc_idx=Integer.parseInt(request.getParameter("sc_idx"));
		
		
		ScheduleDao dao=ScheduleDao.getInstance();
		ScheduleDto schedule=dao.getSchedule(sc_idx);
		
		request.setAttribute("schedule", schedule);
		
		return "/WEB-INF/views/schedule/detailSchedule.jsp";
	}

}
