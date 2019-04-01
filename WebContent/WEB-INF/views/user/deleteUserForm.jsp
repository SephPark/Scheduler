<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete User Form</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/delete.js"></script>
</head>
<body>
<div class="text-center">
	<h2>회원탈퇴</h2>
	<br><br>
	<h3><span class="text-primary">Scheduler </span>와 함께한 <span class="text-primary">${user.name }</span> 님의 추억들을 떠나보내실 건가요?</h3>
</div>
<br><br>
<div class="row">
	<form action="deleteUser.do" method="post" id="delete_form">
	 <div class="form-signin">
	<div class="form-group">
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" maxlength="10" class="form-control" autocomplete="off">
	</div>	
	<div class="form-group">
		<label for="pwd">비밀번호</label>
		<input type="password" name="pwd" id="pwd" maxlength="12" class="form-control">
	</div>
	<div class="form-group">
		<label for="cpwd">비밀번호 확인</label>
		<input type="password" name="cpwd" id="cpwd" maxlength="12" class="form-control">
	</div>
	<br><br>
	<input type="submit" value="회원탈퇴" class="btn btn-primary btn-block">
	<input type="button" value="홈으로" class="btn btn-default btn-block"
		   onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	</form>
</div>
</body>
</html>