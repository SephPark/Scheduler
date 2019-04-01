<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:if test="${check}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <br><br><br><br><br><br>
	<div class="text-center">
		<h1 class="text-center text-info">Scheduler <span class="glyphicon glyphicon-calendar"></span></h1>
		<h1>회원탈퇴가 완료되었습니다.</h1>
		<br><br><br><br>
		<h2><span class="text-primary">Scheduler</span>는 그동안 함께 했던 시간들을 추억하겠습니다.</h2>
		<br><br><br>
	</div><!-- 소개 멘트 끝  -->
	<div class="container">
		<form class="form-signin">
		<button class="btn btn-primary btn-lg btn-block" 
		onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</form>
	</div>	
</div>
</body>
</html>
</c:if>
<c:if test="${!check}">
	<script>
		alert('아이디 또는 비밀번호 불일치!');
		history.go(-1);
	</script>
</c:if>









