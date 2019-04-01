package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//dto 생성
		UserDto user=new UserDto();
		
		//dto에 폼의 데이터를 저장
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPwd(request.getParameter("pwd"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		
		UserDao dao=UserDao.getInstance();
		dao.insertMember(user);
		
		return "/WEB-INF/views/user/registerUser.jsp";
	}

}
