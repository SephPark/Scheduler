package kr.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class CheckDuplicatedEmailAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//email을 가져올 아이디 ?? 브라우저 입력 폼.
		//String id = request.getParameter("id");
		String email=request.getParameter("email");
		
		//DAO 호출
		UserDao dao = UserDao.getInstance();
		UserDto dbEmail=dao.getMemberByEmail(email);
		
		//회원가입 폼에 입력한 이메일과 데이터베이스에 있는 이메일 비교.
		//입력한 이메일을 가진 아이디가 null이면 사용 가능.
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(dbEmail==null) {//email 중복
			mapAjax.put("result", "emailNotFound");
		}else { //email 중복 
			mapAjax.put("result", "emailDuplicated");
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



