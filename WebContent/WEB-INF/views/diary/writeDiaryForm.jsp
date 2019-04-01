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
	<form action="writeDiary.do" method="post" id="write_form">
	<br><br><br>
		<h1 class="text-center">다이어리</h1>
		<br><br>
		<label class="sr-only">제목</label>
		<input type="text" name="d_title" id="d_title" placeholder="제목" class="form-control"
		autocomplete="off">
		<br>
		<label class="sr-only">내용</label>
		<textarea rows="20" cols="30" name="d_content" class="form-control" style="resize:none;"
		placeholder="내용"></textarea>
		<br>
		<div class="align-right">
			<input type="submit" class="btn btn-primary" value="저장">
			<button type="button" class="btn btn-default"
			onclick="location.href='${pageContext.request.contextPath}/main/main.do'">취소</button>
		</div>
	</form>
</div>
</body>
</html>




