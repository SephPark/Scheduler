<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list gallery</title>
<script type="text/javascript">

	
</script>
</head>
<body>
<div class="container">
	<%-- <div class="align-right">
		<input type="button" class="btn btn-primary btn-sm" value="업로드" 
		onclick="location.href='${pageContext.request.contextPath}/gallery/uploadGalleryForm.do'">
	</div> --%>
	<br>
	
	<c:if test="${count==0 }">
	<div class="text-center"><h1>등록된 게시물이 없습니다.</h1></div>
	</c:if>
	
	<c:if test="${count>0 }">
	
	<c:forEach var="gallery" items="${list }">
	<div class="col-sm-3 col-md-3">
		<div class="thumbnail">
			<a href="${pageContext.request.contextPath}/gallery/detailGallery.do?g_idx=${gallery.g_idx }">
			<img src="${pageContext.request.contextPath}/upload/${gallery.g_photo1}"
			class="img-rounded img-responsive" style="max-height: 130px">
			</a>
			<div class="caption">
				<a href="${pageContext.request.contextPath}/gallery/detailGallery.do?g_idx=${gallery.g_idx }">
				<h3>${gallery.g_title}</h3>
				<p>${gallery.g_content}</p>
				</a>		
				<p class="text-right">${gallery.g_reg_date }</p>
			</div>
		</div>
	</div>
	</c:forEach>
	
	<br>
	<div class="align-center" style="clear:both">
		${pagingHtml}
	</div>	
	</c:if>
	
</div>
</body>
</html>