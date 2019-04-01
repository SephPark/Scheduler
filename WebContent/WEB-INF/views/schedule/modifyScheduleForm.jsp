<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/schedule.js"></script>

</head>
<body>
	<div class="">
		<div class="text-center"><h2>일정 수정</h2></div>
			<br>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
				<form action="modifySchedule.do" method="post" id="schedule_form">
				<input type="hidden" name="sc_idx" value="${schedule.sc_idx}">    
					<!-- =============================== -->
					<label for="sc_title" class="">제목</label>
					<input type="text" name="sc_title" id="sc_title" value="${ schedule.sc_title}" class="form-control">
					<br>
					
					<label for="sc_place" class="">장소</label>
					<input type="text" name="sc_place" id="sc_place" value="${schedule.sc_place }" class="form-control">
					<br>
					
					<div class="form-group">
					<label for="sc_all_day">하루종일</label>
						<div class="form-group pull-right">
						
						<input type="checkbox" id="sc_all_day" name="sc_all_day"
						data-toggle="toggle" data-on=" " data-off=" " data-width="40"
							<c:if test="${schedule.sc_all_day=='Y'}">
								checked="checked" 
							</c:if>
						>
						<div id="console-event"></div>
						</div>
					</div>
					<br><br>
					
					<div class="form-group">
					<label for="sc_start">시작</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_start_time" id="sc_start_time" class="form-control"
							value="${schedule.sc_start_time }">
							<input type="date" name="sc_start_date" id="sc_start_date" class="form-control"
							value="${schedule.sc_start_date}">
							</div>
						</div>
					</div>
					
					<br><br><br><br>
					
					
					<div class="form-group">
					<label for="sc_end">종료</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_end_time" id="sc_end_time" class="form-control"
							value="${schedule.sc_end_time}">
							<input type="date" name="sc_end_date" id="sc_end_date" class="form-control"
							value="${schedule.sc_end_date}">
							</div>
						</div>
					</div>
					<br>
					
					<div class="form-group">
						<label for="sc_content" class="">메모</label>
						<textarea rows="5" cols="30" name="sc_content" id="sc_content" 
						class="form-control" style="resize:none;" >${schedule.sc_content}</textarea>
					</div>
					<br>
					
					<input type="submit" class="btn btn-primary btn-block" value="저장">
					<button type="button" class="btn btn-default btn-block" 
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">취소</button>
					<button type="button" class="btn btn-danger btn-block" 
					onclick="location.href='${pageContext.request.contextPath}/schedule/deleteSchedule.do?sc_idx=${schedule.sc_idx }'">삭제</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>