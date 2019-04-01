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
	<div class="container"><!-- 소개 멘트  -->
    <br><br><br><br>
    <h1 class="text-center text-info">Scheduler <span class="glyphicon glyphicon-calendar"></span></h1>
    <h2 class="text-center text-info">회원가입 완료</h2>
  	<br><br><br><br>
    <h3 class="form-signin-heading text-center">이제부터 <span class="text-info">Scheduler </span>에게 당신의 모든 일정을 맡기세요</h3>
	<br><br><br>
	</div>
    <div class="container">
      <form class="form-signin">
            <input type="button" class="btn btn-primary btn-lg btn-block" value="로그인"
            onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </form>

    </div> <!-- /container -->
</div>
</body>
</html>
