<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--========================= view page설정 ======================= --%>
<c:set var="viewPage">
	<jsp:include page="${viewURI}"></jsp:include>
</c:set>
<%--========================= view page설정 ======================= --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Scheduler</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" type="text/css">
<%-- <link href='${pageContext.request.contextPath}/css/fullcalendar.min.css' rel='stylesheet' /> --%>
<link href='${pageContext.request.contextPath}/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />

<link href="sticky-footer.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</head>



<body>
	<div id="main">
		<div class="col-xe-12" id="main_header">
		 <!-- id="main_header" -->
		<div><jsp:include page="header.jsp"/></div>
		</div>
		
        <%-- <div class="col-md-2" >
		<!-- id="main_menu" -->
		<div id="main_menu"><jsp:include page="left.jsp"/></div>
		</div> --%>
		
        <div class="col-sm-12" id="body">
			<div>${viewPage}</div>
		</div>

		<div id="footer"><jsp:include page="footer.jsp"/></div>
	</div>
</body>
</html>


       