<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 상세보기</title>
</head>
<body>
<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-lg-6 col-lg-offset-3">
	<h1 class="text-center">다이어리</h1>
	<br>
	<label class="sr-only">제목</label>
	<input type="text" name="d_title" id="d_title" class="form-control" 
	value="${diary.d_title }" readonly="readonly">
	<br>
	<label class="sr-only">내용</label>
	<textarea rows="20" cols="30" name="d_content" class="form-control" 
	style="resize:none;" readonly="readonly" placeholder="내용">${diary.d_content}</textarea>
	<br>
	<div class="align-right">
		<button type="button" class="btn btn-primary"
		onclick="location.href='${pageContext.request.contextPath}/diary/modifyDiaryForm.do?d_idx=${diary.d_idx }'">수정</button>
		<button type="button" class="btn btn-primary"
		onclick="location.href='${pageContext.request.contextPath}/diary/deleteDiary.do?d_idx=${diary.d_idx }'">삭제</button>
		<button type="button" class="btn btn-default"
		onclick="location.href='${pageContext.request.contextPath}/diary/listDiaryForm.do'">목록</button>
	</div>
</div>
</body>
</html>




