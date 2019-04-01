<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href='${pageContext.request.contextPath}/css/fullcalendar.min.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script>
<style type="text/css">
body {
	margin: 40px 10px;
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
$(function() {

	   var Now = new Date();

	   $.ajax({
	      url : '../schedule/getSchedule.do',
	      dataType : 'json',
	      success : function(data) {

	         var dateArray = [];
	         var list = data.schedule;

	         if(list != null){

	            $(list).each(function(index, item){

	               // 일정 사용 안함은 안보이도록
	               if(item.sc_usable == 'N') return true;

	               var end_date = item.sc_end_date;

	               if(item.sc_start_date != item.sc_end_date) {
	                  var dt = new Date(end_date);
	                  var dayOfMonth = dt.getDate();

	                  dt.setDate(dayOfMonth + 1);

	                  var date = dt.getMonth()+1;
	                  if(date < 10) date = '0' + date;

	                  end_date = dt.getFullYear() + '-' + date + '-' + dt.getDate();
	               }

	               if(item.sc_all_day == 'N'){

	                  dateArray.push({
	                     id : item.sc_idx,
	                     title : item.sc_title,
	                     start : item.sc_start_date + 'T' + item.sc_start_time,
	                     end : end_date + 'T' + item.sc_end_time,
	                     url : '../schedule/detailScheduleForm.do?idx=' + item.sc_idx
	                  });

	               } else {

	                  dateArray.push({
	                     id : item.sc_idx,
	                     title : item.sc_title,
	                     start : item.sc_start_date,
	                     end : end_date,
	                     url : '../schedule/detailScheduleForm.do?idx=' + item.sc_idx
	                  });
	               }

	            })
	         }

	         fullCalendar(dateArray);
	      },
	      error : function() {
	         alert('네트워크 오류 발생');
	      }
	   });

	   function fullCalendar(dateArray){
	      $('#calendar').fullCalendar({
	         customButtons : {
	            registerSchedule : {
	               text : '일정 추가',
	               click : function() {
	                  location.href='../schedule/registerScheduleForm.do';
	               }
	            }
	         },header : {
	            left : 'prev,next today',
	            center : 'title',
	            right : 'month,basicWeek,basicDay,listMonth registerSchedule'
	         },
	         defaultDate : Now.getFullYear() + '-' + (Now.getMonth() + 1) + '-' + Now.getDate(),
	         navLinks : true, // can click day/week names to navigate views
	         editable : true,
	         eventLimit : true, // allow "more" link when too many events
	         events : dateArray
	      });
	   }
	   
	   $('#dialog').dialog({
	      resizable : false,
	      width : 300,
	      position : {
	         of : 'parent'
	      },
	      buttons : {
	         '확인' : function() {
	            $(this).dialog('close');
	         }
	      }
	   });
	});
</script>
<div class="page-main-style">
	<div id='calendar'></div>
</div>