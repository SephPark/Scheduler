<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
</head>
<body>

<div class="container">
<br><br><br>
<h1 class="text-center text-info">Scheduler <span class="glyphicon glyphicon-calendar"></span></h1>
<br>
<h3 class="text-center">회원가입</h3>
<br>
	<form action="registerUser.do" method="post" id="register_form" class="form-signin">

			<label class="sr-only" for="id">아이디</label>
			<input type="text" name="id" id="id"  maxlength="12" autocomplete="off"
			class="form-control" placeholder="ID" autofocus="">
			<input type="button" value="ID중복체크" id="id_check" class="btn btn-default btn-block">
					<span id="message_id"></span>
			<br>
			<label for="name" class="sr-only">이름</label>
			<input type="text" name="name" id="name" maxlength="10" class="form-control" placeholder="이름" autocomplete="off">
			<br>	
			<label for="pwd" class="sr-only">비밀번호</label>
			<input type="password" name="pwd" id="pwd" maxlength="12" class="form-control" placeholder="Password">
			<br>
			<label for="phone" class="sr-only">전화번호</label>
			<input type="text" name="phone" id="phone" maxlength="15" class="form-control" placeholder="123-1234-1234" autocomplete="off">
			<br>
			<label class="sr-only">이메일</label>
			<input type="email" name="email" id="email" maxlength="50" class="form-control" placeholder="example@abc.com" autocomplete="off" required="">
					<span id="message_email"></span>
			<input type="button" value="Email중복체크" id="email_check" class="btn btn-default btn-block">
			<br><br>
	<div class="align-center">
		<input type="submit" value="가입" class="btn btn-primary btn-block btn-lg">
		<input type="button" value="뒤로가기" class="btn btn-default btn-block btn-lg"
		onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	</form>
	</div>
</body>
</html>