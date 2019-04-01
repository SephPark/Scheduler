<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
 		<!-- 로그인이 안된 상태 -->
	<%-- <c:if test="${empty user_id }">
	<li><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></li>
	<li><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></li>
	</c:if> --%>
			<!-- 로그인이 된 상태 -->
		
	<%-- <c:if test="${!empty user_id && user_auth==1 }">  --%>
	
	
	<li>${user_id}님 로그인 중</li>
	<li><a href="${pageContext.request.contextPath}/user/logout.do">로그아웃</a></li>
	<li><a href="${pageContext.request.contextPath}/user/modifyUserForm.do">회원정보 수정</a></li>
	<li><a href="${pageContext.request.contextPath}/user/deleteUserForm.do">회원탈퇴</a></li>
	
	<%-- </c:if> --%>
	
</ul>
	
	<!-- 시계  -->

	<!-- 가장 빠른 일정 3개 -->
	
