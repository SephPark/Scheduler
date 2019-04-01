package kr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;
import kr.util.AuthUtil;

public class DeleteUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//
		
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DAO 호출
		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getMember(id);
		boolean check = false;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user!=null && user_id.equals(id)) {
			//비밀번호 일치 여부 체크
			check = user.isCheckedPasswd(pwd);
		}
		
		if(check) {
			//회원정보삭제
			dao.deleteMember(id);
			//로그아웃
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/user/deleteUser.jsp";
	}

}



