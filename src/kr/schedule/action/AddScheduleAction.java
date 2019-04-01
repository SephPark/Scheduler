package kr.schedule.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;
import kr.schedule.domain.ScheduleInvitedUserDto;
import kr.util.AuthUtil;

public class AddScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//==========로그인 체크 시작=========//
//		if(!AuthUtil.isLogin(request)) {
//			return "redirect:/user/loginForm.do";
//		}
		//=========로그인 체크 끝===========//
		
		Map<String , String>mapAjax=new HashMap<String, String>();
		HttpSession session = request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		ScheduleDao dao=ScheduleDao.getInstance();
		int sc_idx=dao.getSc_idx();
		
		ScheduleDto schedule=new ScheduleDto();
		schedule.setSc_title(request.getParameter("sc_title"));
		schedule.setId(AuthUtil.getUser_id(request));
		schedule.setSc_place(request.getParameter("sc_place"));
		schedule.setSc_start_date(request.getParameter("sc_start_date"));
		schedule.setSc_start_time(request.getParameter("sc_start_time"));
		schedule.setSc_end_date(request.getParameter("sc_end_date"));
		schedule.setSc_end_time(request.getParameter("sc_end_time"));
		schedule.setSc_content(request.getParameter("sc_content"));
		//schedule.setSc_all_day(request.getParameter("allDay"));
		if (request.getParameter("sc_all_day")==null) {
			schedule.setSc_all_day("N");
		}else {
			schedule.setSc_all_day("Y");
		}
		
		dao.addSchedule(schedule);
		
		/*
		 * mapAjax.put("result", "success");
		 * 
		 * ObjectMapper mapper=new ObjectMapper();
		 * 
		 * String jsonData=mapper.writeValueAsString(mapAjax);
		 * 
		 * request.setAttribute("jsonData", jsonData);
		 */
		
//		AlarmDto alarm=new AlarmDto();
//		alarm.setAl_timer(Integer.parseInt(request.getParameter("al_timer")));
//		alarm.setSc_idx(sc_idx);
	
		List<ScheduleDto> list=null;
		//받을 리스트 생성.
		List<ScheduleInvitedUserDto> scheduleInvitedUserDtoList=new ArrayList<ScheduleInvitedUserDto>();
		//for(넣을 dto : 반복할 list){
		//받을 dto 생성자 = new 받을 dto;  
		//받을 생성자  }
		
//		for(ScheduleDto :list) {
//			ScheduleInvitedUserDto invitedUserDto =new ScheduleInvitedUserDto();
//			invitedUserDto.setSc_idx(sc_idx);
//			//invitedUserDto.setId();
//			
//			scheduleInvitedUserDtoList.add(invitedUserDto);
//		}
		
		return "/WEB-INF/views/schedule/addSchedule.jsp";
	}

}
