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
		
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========�α��� üũ ��===========//
		
		//���۵� ������ ��ȯ
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DAO ȣ��
		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getMember(id);
		boolean check = false;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		if(user!=null && user_id.equals(id)) {
			//��й�ȣ ��ġ ���� üũ
			check = user.isCheckedPasswd(pwd);
		}
		
		if(check) {
			//ȸ����������
			dao.deleteMember(id);
			//�α׾ƿ�
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/user/deleteUser.jsp";
	}

}



