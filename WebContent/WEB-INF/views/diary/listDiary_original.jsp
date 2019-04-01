<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div class="col-md-6 col-md-offset-3">
	<div class="text-center"><h2>Diary</h2></div>
	<div class="list-space align-right">
		<input type="button" value="다이어리 쓰기" class="btn btn-primary" 
		onclick="location.href='${pageContext.request.contextPath}/diary/writeDiaryForm.do'">
		     
	</div>
	<c:if test="${count == 0}">
	<div class="result-display">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>작성일</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="diary" items="${list}">
		<tr>
			<td>${diary.d_reg_date}</td>
			<td><a href="detailDiary.do?d_idx=${diary.d_idx}">${diary.d_title}</a></td>
			<td>${board.id}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">
		${pagingHtml}
	</div>	
	</c:if>
</div>
</body>
</html>








