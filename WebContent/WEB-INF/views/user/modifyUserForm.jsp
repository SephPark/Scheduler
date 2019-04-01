<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">
	<div class="">
	<br><br>
		<div class="text-center"><h2>회원정보 수정</h2></div>
		<br><br>
		<form action="modifyUser.do" method="post" id="modify_form" >
			<div class="form-signin">
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="${user.name}" 
				maxlength="10" class="form-control">
				<br>
				
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd" maxlength="12" class="form-control">
				<br>
				
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" value="${user.phone}" 
				maxlength="15" class="form-control">
				<br>
				
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" value="${user.email}" 
				maxlength="50" class="form-control">
				<br><br>

				<input type="submit" value="수정" class="btn btn-primary btn-block">
				<input type="button" value="홈으로" class="btn btn-default btn-block"
				   onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				<input type="button" value="회원탈퇴" class="btn btn-default btn-block"
				   onclick="location.href='${pageContext.request.contextPath}/user/deleteUserForm.do'">   
			</div>   
		</form>
	</div><!-- end of panel -->	
</div>
</body>
</html>