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
<c:set var="now" value="<%=new java.util.Date()%>" />
</head>
<body>
	<div class="">
		<div class="text-center"><h2>일정 상세 보기</h2></div>
		<input type="hidden" name="sc_idx" value="${schedule.sc_idx}">    
			<br>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
					<!-- =============================== -->
					<label for="sc_title">제목</label>
					<input type="text" name="sc_title" id="sc_title"  class="form-control" 
					value="${schedule.sc_title }" readonly="readonly">
					<br>
					
					<label for="sc_place">장소</label>
					<input type="text" name="sc_place" id="sc_place" value="${schedule.sc_place}" 
					class="form-control" readonly="readonly">
					<br>
					
					<div class="form-group">
					<label for="sc_all_day">하루종일</label>
						<div class="form-group pull-right">
						<input type="checkbox" data-toggle="toggle" data-on=" " data-off=" " data-width="40"
						id="sc_all_day" disabled="disabled"
						 <c:if test="${schedule.sc_all_day=='Y' }">
						checked="checked"
						 </c:if>
						>
						<div id="console-event"></div>
						</div>
					</div>
					<br><br>
					
					<!-- <script>
						$(document).on('click', '.toggle-group label', function(){
							if ($("#sc_all_day").is(":checked")) {

								$("#allDay").val("Y");
							} else {
								$("#allDay").val("N");
							}
						})
					</script> -->
					
					
					<div class="form-group">
					<label for="sc_start">시작</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_start_time" id="sc_start_time" class="form-control"
							value="${schedule.sc_start_time}" readonly="readonly">
							<input type="date" name="sc_start_date" id="sc_start_date" class="form-control"
							value="${schedule.sc_start_date}" readonly="readonly">
							</div>
						</div>
					</div>
					<br><br><br><br>
					
					
					<div class="form-group">
					<label for="sc_end">종료</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_end_time" id="sc_end_time" class="form-control"
							value="${schedule.sc_end_time}" readonly="readonly">
							<input type="date" name="sc_end_date" id="sc_end_date" class="form-control"
							value="${schedule.sc_end_date}" readonly="readonly">
							</div>
						</div>
					</div>
					<br>
					
					<div class="form-group">
						<label for="sc_content">메모</label>
						<textarea rows="5" cols="30" name="sc_content" id="sc_content" 
						class="form-control" style="resize:none;" readonly="readonly">${schedule.sc_content }</textarea>
					</div>
					<br>
					
					<button type="submit" class="btn btn-primary btn-block" 
					onclick="location.href='${pageContext.request.contextPath}/schedule/modifyScheduleForm.do?sc_idx=${schedule.sc_idx }'">수정</button>
					<button type="button" class="btn btn-default btn-block" 
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">캘린더</button>
			</div>
		</div>
	</div>
</body>
</html>