package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;
import kr.util.AuthUtil;

public class ModifyUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//========�α��� üũ ����==========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=======�α��� üũ ��============//
		
		UserDao dao = UserDao.getInstance();
		HttpSession session = request.getSession();
		UserDto user = 
				dao.getMember(
			(String)session.getAttribute("user_id"));
		
		request.setAttribute("user", user);
		
		return "/WEB-INF/views/user/modifyUserForm.jsp";
	}

}




