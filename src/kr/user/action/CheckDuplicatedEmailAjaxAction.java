package kr.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class CheckDuplicatedEmailAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//email�� ������ ���̵� ?? ������ �Է� ��.
		//String id = request.getParameter("id");
		String email=request.getParameter("email");
		
		//DAO ȣ��
		UserDao dao = UserDao.getInstance();
		UserDto dbEmail=dao.getMemberByEmail(email);
		
		//ȸ������ ���� �Է��� �̸��ϰ� �����ͺ��̽��� �ִ� �̸��� ��.
		//�Է��� �̸����� ���� ���̵� null�̸� ��� ����.
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(dbEmail==null) {//email �ߺ�
			mapAjax.put("result", "emailNotFound");
		}else { //email �ߺ� 
			mapAjax.put("result", "emailDuplicated");
		}
		
		/*
		 * JSON �������� ��ȯ�ϱ⸦ ���ϴ� ���ڿ��� HashMap��
		 * key�� value ������ ������ �� ObjectMapper��
		 * writeValueAsString�� Map ��ü�� �����ؼ�
		 * �Ϲ� ���ڿ� �����͸� JSON ������ ���ڿ� �����ͷ�
		 * ��ȯ�ؼ� ��ȯ
		 */
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("jsonData", jsonData);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}



