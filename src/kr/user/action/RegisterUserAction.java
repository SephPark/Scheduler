package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//dto ����
		UserDto user=new UserDto();
		
		//dto�� ���� �����͸� ����
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
