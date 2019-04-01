package kr.main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.util.AuthUtil;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (AuthUtil.isLogin(request)==false)
			return "/WEB-INF/views/user/loginForm.jsp";

		return "/WEB-INF/views/main/main.jsp";
	}
}




