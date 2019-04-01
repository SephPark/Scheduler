package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class RegisterUserFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(33333);
		System.out.println(4444);
		return "/WEB-INF/views/member/registerUserForm.jsp";
		
	}

}
