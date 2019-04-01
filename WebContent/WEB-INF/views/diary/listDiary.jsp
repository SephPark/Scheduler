<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='${pageContext.request.contextPath}/css/diary.min.css' rel='stylesheet' />
<style type="text/css">
body {
	/* margin: 40px 10px; */
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<script type="text/javascript">

	var Now = new Date();
	
	$(document).ready(function() {
	 	//diary데이터 호출
		$.ajax({
			url:'../diary/listDiary.do',
			type:'post',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var list=data.list;
				
				var dateArray= [];
				
				$(list).each(function(index,diary){
					var d_title=diary.d_title;
					var d_reg_date=diary.d_reg_date;
					var d_idx=diary.d_idx;
					
					dateArray.push({
						title:d_title,
						start:d_reg_date,
						url:'${pageContext.request.contextPath}/diary/detailDiary.do?d_idx='+diary.d_idx
					});
				})
				
				fullCalendar(dateArray);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	 	//함수를 만들었다. (인자는 어레이로)
		function fullCalendar(dateArray){
			//캘린더 
			$('#calendar').fullCalendar({
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'month'	//,basicWeek,basicDay'
				},
				defaultDate : Now.getFullYear() + '-' + (Now.getMonth() + 1) + '-' + Now.getDate(),
				navLinks : true, // can click day/week names to navigate views
				editable : true,
				eventLimit : true, // allow "more" link when too many events
				//어레이를 만들어서 내보내기.
				events : dateArray
			});
		}
	});
</script>
<div class="page-main-style">
	<div class="text-center sr-only"><h1>다이어리</h1></div>
	<br>
	<div id='calendar'></div>
</div>
<br><br><br>

