package kr.gallery.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.gallery.dao.GalleryDao;
import kr.gallery.domain.GalleryDto;
import kr.util.AuthUtil;
import kr.util.PagingUtil;

public class ListGalleryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id=AuthUtil.getUser_id(request);
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) pageNum = "1";
		
		int rowCount = 8; //한 페이지의 게시물 수
		int pageCount = 5; //한 화면의 페이지 수
		int currentPage = Integer.parseInt(pageNum);
		
		//DAO 호출
		GalleryDao dao = GalleryDao.getInstance();
		int count = dao.getGalleryCount(id);

		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,"listGallery.do");
		List<GalleryDto> list = null;
		if(count > 0) {
			list = dao.getGalleryList(
					page.getStartCount(), 
					page.getEndCount(),
					id
					);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/gallery/listGallery.jsp";
	}

}
