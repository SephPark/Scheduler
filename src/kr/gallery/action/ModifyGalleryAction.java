package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;
import kr.util.FileUtil;

public class ModifyGalleryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========로그인 체크 시작=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/member/loginForm.do";
		}
		//=========로그인 체크 끝===========//	
		System.out.println("===modify Gallery action=====");
		MultipartRequest multi = FileUtil.createFile(request);
		int g_idx=Integer.parseInt(multi.getParameter("g_idx"));
		System.out.println("========"+g_idx+"=================");
		
		

		GalleryDao dao=GalleryDao.getInstance();
		//db에 있던 gallery
		GalleryDto dbGallery=dao.getGalleryDetail(g_idx);
		
		if(!AuthUtil.isAuthWriter(request, dbGallery.getId())) {
			return "/WEB-INF/views/common/notice.jsp";
		}

		GalleryDto gallery=new GalleryDto();

		gallery.setG_title(multi.getParameter("g_title"));
		gallery.setG_content(multi.getParameter("g_content"));
		gallery.setG_idx(g_idx);
		
		if (multi.getFilesystemName("g_photo1")==null) {
			//db에 있던이름을 그대로 사용.
			gallery.setG_photo1(dbGallery.getG_photo1());
		}else {
			gallery.setG_photo1(multi.getFilesystemName("g_photo1"));
		}

		dao.updateGallery(gallery);

		return "redirect:/gallery/listGallery.do";	
	}

}
