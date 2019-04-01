package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;
import kr.util.FileUtil;

public class UploadGalleryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//==========�α��� üũ ����=========//
		if(!AuthUtil.isLogin(request)) {
			return "redirect:/user/loginForm.do";
		}
		//=========�α��� üũ ��===========//

		MultipartRequest multi = FileUtil.createFile(request);
		//DTO ����
		GalleryDto gallery= new GalleryDto();

		gallery.setG_title(multi.getParameter("g_title"));
		gallery.setG_content(multi.getParameter("g_content"));
		gallery.setG_photo1(multi.getFilesystemName("g_photo1"));
		gallery.setId(AuthUtil.getUser_id(request));
		

		//DAO ����
		GalleryDao dao = GalleryDao.getInstance();
		dao.uploadGallery(gallery);

		//return "/WEB-INF/views/main/main.jsp";
		return "redirect:/gallery/listGallery.do";
	}

}
