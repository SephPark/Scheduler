package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;
import kr.util.AuthUtil;

public class ModifyUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=======�α��� üũ ��============//

		HttpSession session = request.getSession();
		UserDto user = new UserDto();
		user.setId((String)session.getAttribute("user_id"));
		user.setName(request.getParameter("name"));
		user.setPwd(request.getParameter("pwd"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));

		//DAO ȣ��
		UserDao dao = UserDao.getInstance();
		dao.updateMember(user);

		return "/WEB-INF/views/user/modifyUser.jsp";
	}
}







