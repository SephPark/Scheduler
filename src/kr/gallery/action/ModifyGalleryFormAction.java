package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;

public class ModifyGalleryFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========�α��� üũ ��===========//

		int g_idx=Integer.parseInt(request.getParameter("g_idx"));
		GalleryDao dao=GalleryDao.getInstance();
		GalleryDto gallery=dao.getGalleryDetail(g_idx);

		System.out.println(g_idx);
		
		if (!AuthUtil.isAuthWriter(request, gallery.getId())) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		request.setAttribute("gallery", gallery);

		return "/WEB-INF/views/gallery/modifyGalleryForm.jsp";
	}

}
