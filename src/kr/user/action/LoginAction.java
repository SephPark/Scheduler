package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("login action");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		UserDao dao=UserDao.getInstance();
		UserDto user = dao.getMember(id);
		boolean check = false;
		
		if (user!=null) {
			check=user.isCheckedPasswd(pwd);
		}
		
		if (check) {
			//true가 들어오면 세션에 로그인 정보 저장한다.
			HttpSession session=request.getSession();
			session.setAttribute("user_id", id);
			session.setAttribute("user_auth", user.getAuth());
		}
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/user/login.jsp";
	}

}
