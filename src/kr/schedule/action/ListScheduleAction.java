package kr.schedule.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.javafx.collections.MappingChange.Map;

import kr.controller.Action;
import kr.schedule.dao.ScheduleDao;
import kr.schedule.domain.ScheduleDto;
import kr.util.AuthUtil;
  
public class ListScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id=AuthUtil.getUser_id(request);
		ScheduleDao dao=ScheduleDao.getInstance();
		
		List<ScheduleDto> list=null;
		list=dao.getSchedule(id);
		
		HashMap<String, Object> mapAjax=new HashMap<String,Object>();
		
		mapAjax.put("list", list);
		
		ObjectMapper mapper=new ObjectMapper();
		
		String jsonData=mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}
