<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div class="text-center"><h2>새로운 일정</h2></div>
			<br>
		<div class="row">
		<div class="col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
				<form action="addSchedule.do" method="post" id="schedule_form">
					<!-- =============================== -->
					<label for="sc_title" class="sr-only">제목</label>
					<input type="text" name="sc_title" id="sc_title" placeholder="제목" class="form-control">
					<br>
					
					<label for="sc_place" class="sr-only">장소</label>
					<input type="text" name="sc_place" id="sc_place" placeholder="장소" class="form-control">
					<br>
					
					<div class="form-group">
					<label for="sc_all_day">하루종일</label>
						<div class="form-group pull-right">
						<input type="checkbox" data-toggle="toggle" data-on=" " data-off=" " data-width="40"
						id="sc_all_day">
						<div id="console-event"></div>
						</div>
					</div>
					<br><br>
					
			<!-- 		<script>
						$(document).on('click', '.toggle-group label', function(){
							if ($("#sc_all_day").is(":checked")) {

								$("#allDay").val("Y");
							} else {
								$("#allDay").val("N");
							}
						})
					</script>
 -->					
					
					<div class="form-group">
					<label for="sc_start">시작</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_start_time" id="sc_start_time" class="form-control" value="<fmt:formatDate value="${now}" pattern="HH:mm" />">
							<input type="date" name="sc_start_date" id="sc_start_date" class="form-control" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />">
							</div>
						</div>
					</div>
					<br><br><br><br>
					
					<div class="form-group">
					<label for="sc_end">종료</label>
						<div class="form-inline">
							<div class="form-group pull-right">
							<input type="time" name="sc_end_time" id="sc_end_time" class="form-control" value="<fmt:formatDate value="${now}" pattern="HH:mm" />">
							<input type="date" name="sc_end_date" id="sc_end_date" class="form-control" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />">
							</div>
						</div>
					</div>
					<br><br>
					
					<!-- <script>
					document.getElementById('sc_start_date').value = new Date().toISOString().substring(0, 10);
					document.getElementById('sc_start_time').value = new Date().toISOString().slice(11, 16);
					document.getElementById('sc_end_date').value = new Date().toISOString().substring(0, 10);
					document.getElementById('sc_end_time').value = new Date().toISOString().slice(11, 16);
					
					document.getElementById('sc_end_time').value = new Date().toISOString().slice(11, 16); 
					
					var time = new Date();
					console.log(
					  time.toLocaleString('en-US', { hour: 'numeric', hour12: true })
					);  
					</script> -->
					
					<!-- <div class="form-group">
					<label for="al_timer" >알림 시간</label>
						<div class="form-group pull-right">
						<input type="number" min="1" max="24"  name="al_timer" id="al_timer" class="form-control">
						<select name="al_timer" id="al_timer">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
						</select>
						</div>
					</div> -->
					<br>
					
					<div class="form-group">
						<label for="sc_content" class="sr-only">메모</label>
						<textarea rows="5" cols="30" name="sc_content" id="sc_content" 
						class="form-control" style="resize:none;" placeholder="메모" ></textarea>
					</div>
					<br><br>
					
					<input type="submit" class="btn btn-primary btn-block" value="저장">
					<button type="button" class="btn btn-default btn-block" 
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">취소</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>