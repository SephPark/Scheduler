<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container"><!-- 소개 멘트  -->
    <br><br><br><br><br>
    <h1 class="text-center text-info">Scheduler <span class="glyphicon glyphicon-calendar"></span></h1>
  	<br><br>
    <h3 class="form-signin-heading text-center"><span class="text-info"><b>Scheduler </b></span>와 함께 효율적으로 일정을 관리하고 설계하세요</h3>
	<br><br><br>
	</div>
    <div class="container"><!-- 입력 폼 시작  -->
      <form class="form-signin" action="login.do" method="post" id="login_form">
        <label for="id" class="sr-only">ID</label>
        <input type="text" id="id" name="id" class="form-control" placeholder="ID" autofocus="">
        
        <label for="pwd" class="sr-only">Password</label>
        <input type="password" id="pwd" name="pwd" class="form-control" placeholder="Password" >
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
         	<br><br><br>
         	<div class="text-center">
         		<a href="${pageContext.request.contextPath}/user/registerUserForm.do">
         		<h4>계정이 아직 없다면?</h4>
         		</a>
       		</div>
         	<br>
            <input type="button" class="btn btn-default btn-lg btn-block" value="회원가입"
            onclick="location.href='${pageContext.request.contextPath}/user/registerUserForm.do'">
      </form>

    </div> <!-- /container -->
</body>
</html>