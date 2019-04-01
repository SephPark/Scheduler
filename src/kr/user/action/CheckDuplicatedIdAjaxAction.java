package kr.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class CheckDuplicatedIdAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		//DAO 호출
		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getMember(id);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(user == null) {//아이디 미중복
			mapAjax.put("result", "idNotFound");
		}else { //아이디 중복
			mapAjax.put("result", "idDuplicated");
		}
		
		/*
		 * JSON 형식으로 변환하기를 원하는 문자열을 HashMap에
		 * key와 value 쌍으로 저장한 후 ObjectMapper의
		 * writeValueAsString에 Map 객체를 전달해서
		 * 일반 문자열 데이터를 JSON 형식의 문자열 데이터로
		 * 변환해서 반환
		 */
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}



