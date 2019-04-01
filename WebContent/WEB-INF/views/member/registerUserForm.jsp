<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<h2>회원가입</h2>
<form action="registerUser.do" method="post" id="register_form">
<ul>
	<li>
		<label>아이디</label>
		<input type="text" name="id" id="id" size="6" maxlength="12" autocomplete="off">
	</li>
	<li>
		<label for="name">이름</label>
		<input type="text" name="name" id="name" maxlength="10">
	</li>
	<li>
		<label for="pwd">비밀번호</label>
		<input type="password" name="pwd" id="pwd" maxlength="12">
	</li>
	<li>
		<label for="phone">전화번호</label>
		<input type="text" name="phone" id="phone" maxlength="15">
	</li>
	<li>
		<label for="email">이메일</label>
		<input type="email" name="email" id="email" maxlength="50">
	</li>	
</ul>
<div class="align-center">
	<input type="submit" value="가입">
	<input type="button" value="홈으로"
	onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
</div>    
</form>
</body>
</html>