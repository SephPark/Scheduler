<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-lg-6 col-lg-offset-3">
	<form action="modifyDiary.do" method="post" id="write_form">
		<input type="hidden" name="d_idx" value="${diary.d_idx}">    
		<h1 class="text-center">다이어리 수정</h1>
		<br>
		<label class="sr-only">제목</label>
		<input type="text" name="d_title" id="d_title" value="${diary.d_title }" class="form-control">
		<br>
		<label class="sr-only">내용</label>
		<textarea rows="20" cols="30" name="d_content" class="form-control" style="resize:none;"
		placeholder="내용">${diary.d_content}</textarea>
		<br>
		<div class="align-right">
			<input type="submit" class="btn btn-primary" value="저장">
			<button type="button" class="btn btn-primary"
			onclick="location.href='${pageContext.request.contextPath}/diary/deleteDiary.do?d_idx=${diary.d_idx }'">삭제</button>
			<button type="button" class="btn btn-default"
			onclick="location.href='${pageContext.request.contextPath}/diary/listDiary.do'">취소</button>
		</div>
	</form>
	
</div>
</body>
</html>




