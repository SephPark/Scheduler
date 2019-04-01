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
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		int g_idx=Integer.parseInt(request.getParameter("g_idx"));
		GalleryDao dao=GalleryDao.getInstance();
		GalleryDto gallery=dao.getGalleryDetail(g_idx);

		//로그인한 ID와 작성자 ID 일치 여부
		if(!AuthUtil.isAuthWriter(request, gallery.getId())) {
			//로그인한 ID와 작성자 ID 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}

		dao.deleteGallery(g_idx);

		return "redirect:/gallery/listGallery.do";	}

}
