package kr.member.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.domain.MemberDto;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//dto ����
		MemberDto member=new MemberDto();
		
		//dto�� ���� �����͸� ����
		member.setId(request.getParameter("id"));
		member.setName(request.getParameter("name"));
		member.setPwd(request.getParameter("pwd"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		
		MemberDao dao=MemberDao.getInstance();
		dao.insertMember(member);
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
