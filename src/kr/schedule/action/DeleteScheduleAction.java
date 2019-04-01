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
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		
		int sc_idx=Integer.parseInt(request.getParameter("sc_idx"));
		ScheduleDao dao=ScheduleDao.getInstance();
		ScheduleDto schedule=dao.getSchedule(sc_idx);
		
		//�α����� ID�� �ۼ��� ID ��ġ ����
		if(!AuthUtil.isAuthWriter(request, schedule.getId())) {
			//�α����� ID�� �ۼ��� ID ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		dao.deleteSchedule(sc_idx);
		
		return "redirect:/main/main.do";
	}

}
