package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;

public class DeleteGalleryAction implements Action {

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

		//�α����� ID�� �ۼ��� ID ��ġ ����
		if(!AuthUtil.isAuthWriter(request, gallery.getId())) {
			//�α����� ID�� �ۼ��� ID ����ġ
			return "/WEB-INF/views/common/notice.jsp";
		}

		dao.deleteGallery(g_idx);

		return "redirect:/gallery/listGallery.do";	}

}
