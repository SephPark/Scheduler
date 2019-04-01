package kr.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.util.FileUtil;

public class ImageUploaderAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartRequest multi = FileUtil.createFile(request);
		String g_photo1 = multi.getFilesystemName("file");
		
		request.setAttribute("jsonData", request.getContextPath()+"/upload/"+g_photo1);
		
		return "/WEB-INF/views/common/ajaxView.jsp";
	}

}
