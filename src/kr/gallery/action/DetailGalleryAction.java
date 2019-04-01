package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;

public class DetailGalleryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========로그인 체크 끝===========//

		GalleryDao dao=GalleryDao.getInstance();

		int g_idx=Integer.parseInt(request.getParameter("g_idx"));


		dao.getGalleryDetail(g_idx);

		System.out.println(g_idx);

		GalleryDto gallery=dao.getGalleryDetail(g_idx);

		//diary.setD_title(StringUtil.useNoHtml(diary.getD_title()));
		//diary.setD_content(StringUtil.useBrNoHtml(diary.getD_content()));

		request.setAttribute("gallery", gallery);

		return "/WEB-INF/views/gallery/detailGallery.jsp";	}

}
