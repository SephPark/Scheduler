<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='${pageContext.request.contextPath}/css/fullcalendar.min.css' rel='stylesheet' />
<%--     
<div class="page-main-style">
	메인에 보여질 내용
	Scheduler Calendar
	
</div>

 --%>


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
	$.ajax({
		url:'../schedule/listSchedule.do',
		type:'post',
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){
			var list=data.list;
			var dateArray= [];
			
			$(list).each(function(index,schedule){
				var sc_title=schedule.sc_title;
				var sc_start=schedule.sc_start;	
				var sc_end=schedule.sc_end;
				
				var endYear = sc_end.substr(0,4);
				var endMonth = sc_end.substr(5,2);
				var endDay = parseInt(sc_end.substr(8,2)) + 1;
				
				endDay = moment(new Date(endYear, endMonth-1, endDay)).format('YYYY-MM-DD');
				
				dateArray.push({
					title:sc_title,
					start:sc_start,
					end:endDay,
					url : '${pageContext.request.contextPath}/schedule/detailSchedule.do?sc_idx='+schedule.sc_idx
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

//=======================

/* 	var Now = new Date();
	
	//$(document).ready(function() {
	 	//schedule데이터 호출
		$.ajax({
			url:'../schedule/listSchedule.do',
			type:'post',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var dateArray = [];
				var list=data.list;
				
				if (list !=null) {
					$(list).each(function(index,schedule){
						// 일정 사용 안함은 안보이도록
			            if(schedule.sc_usable == 'N') return true;
						
						var end_date=schedule.sc_end_date;
						
						alert(schedule.sc_start_date);
						
						//시작 날짜와 끝 날짜가 같은 경우
						if (schedule.sc_start_date!=schedule.sc_end_date) {
							var endDate=new Date(end_date);
							var dayOfMonth=endDate.getDate();
							
							endDate.setDate(dayOfMonth+1);
							
							var date=endDate.getMonth()+1;
							if (date<10) date='0'+date;
							
							end_date=endDate.getFullYear()+'-'+date+'-'+endDate.getDate();
						}
						
						if (schedule.sc_all_day=='N') {
							dateArray.push({
			                     id : schedule.sc_idx,
			                     title : schedule.sc_title,
			                     start : schedule.sc_start_date + 'T' + schedule.sc_start_time,
			                     end : end_date + 'T' + schedule.sc_end_time,
			                     url : '${pageContext.request.contextPath}/schedule/detailSchedule.do?idx='+schedule.sc_idx
			                  });
						}else{
							dateArray.push({
								id : schedule.sc_idx,
								title : schedule.sc_title,
								start : schedule.sc_start_date,
								end : end_date,
								url : '${pageContext.request.contextPath}/schedule/detailSchedule.do?idx='+schedule.sc_idx
							});
						}
						 output.push({
							title:sc_title,
							start:sc_start,
							end:sc_end,
							url:'${pageContext.request.contextPath}/schedule/detailSchedule.do?sc_idx='+schedule.sc_idx
						}); 
					})//end of (list)each
				}//end of if
				
				
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
	//});  */
	
</script>
<div class="page-main-style">
	<br>
	<div id='calendar'></div>
</div>
<br><br><br>
