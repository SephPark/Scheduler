package kr.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.user.dao.UserDao;
import kr.user.domain.UserDto;

public class CheckDuplicatedIdAjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		//DAO ȣ��
		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getMember(id);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(user == null) {//���̵� ���ߺ�
			mapAjax.put("result", "idNotFound");
		}else { //���̵� �ߺ�
			mapAjax.put("result", "idDuplicated");
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



