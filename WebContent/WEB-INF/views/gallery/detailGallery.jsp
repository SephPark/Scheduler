<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 상세보기</title>
</head>
<body>
<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
	<br>
	<div>
		<img src="${pageContext.request.contextPath}/upload/${gallery.g_photo1}"
		class="img-responsive center-block" height="80%">
	</div>
	<br>
	<div class="panel panel-default">
		<div class="panel-heading">${gallery.g_title }<div class="text-right">게시일 ${gallery.g_reg_date }</div></div>
		<div class="panel-body">${gallery.g_content}</div>
	</div>
	
	<div class="align-right">
		<button type="button" class="btn btn-primary"
		onclick="location.href='${pageContext.request.contextPath}/gallery/modifyGalleryForm.do?g_idx=${gallery.g_idx }'">
		수정</button>
		<button type="button" class="btn btn-danger"
		onclick="location.href='${pageContext.request.contextPath}/gallery/deleteGallery.do?g_idx=${gallery.g_idx }'">
		삭제</button>
		<button type="button" class="btn btn-default"
		onclick="location.href='${pageContext.request.contextPath}/gallery/listGallery.do'">
		목록</button>
	</div>
	
</div>
</body>
</html>