<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <h3 class="text-left">Scheduler </h3> -->

<%-- <div class="row">
	<!-- <div class="col-md-4 col-md-offset-9"> -->
	<a href="${pageContext.request.contextPath }/board/list.do">Schedule</a>
	<a href="${pageContext.request.contextPath }/item/itemList.do">Diary</a>
	<a href="${pageContext.request.contextPath}/cart/list.do">Gallery</a>
	<a href="${pageContext.request.contextPath}/main/main.do">Main</a>
	<!-- </div> -->
</div> --%>



<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
        <button type="button" class="navbar-toggle"
			data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			
          <a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.do">
          	Scheduler <span class="glyphicon glyphicon-calendar"></span>
          		
          </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
	            	<label class="sr-only">일정관리</label>
	            	<span class="glyphicon glyphicon-calendar"></span>
	            	<span class="caret"></span>
            	</a>
            	<ul class="dropdown-menu">
            		<li><a href="${pageContext.request.contextPath}/schedule/addScheduleForm.do" >
            		<span class="glyphicon glyphicon-plus"></span> 일정추가</a></li>
            		<li><a href="${pageContext.request.contextPath}/main/main.do" >
            		<span class="glyphicon glyphicon-calendar"></span> 캘린더</a></li>
            	</ul>
            </li>
            
            <li class="dropdwon"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
            	<label class="sr-only">다이어리</label>
            	<span class="glyphicon glyphicon-book">
            	</span><span class="caret"></span></a>
            	<ul class="dropdown-menu">
            		<li><a href="${pageContext.request.contextPath}/diary/writeDiaryForm.do">
            		<span class="glyphicon glyphicon-pencil"></span> 다이어리 작성</a></li>
            		<li><a href="${pageContext.request.contextPath}/diary/listDiaryForm.do">
            		<span class="glyphicon glyphicon-th-list"></span> 다이어리 리스트</a></li>
            	</ul>
            </li>
            
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<label class="sr-only">갤러리</label>
            		<span class="glyphicon glyphicon-picture"> </span>
            		<span class="caret dropdown"></span>
            	</a>
            	<ul class="dropdown-menu">
            		<li><a href="${pageContext.request.contextPath}/gallery/uploadGalleryForm.do">
            		<span class="glyphicon glyphicon-upload"></span> 업로드</a></li>
            		<li><a href="${pageContext.request.contextPath}/gallery/listGallery.do">
            		<span class="glyphicon glyphicon-th"></span> 갤러리</a></li>
            	</ul>
            </li>
            
            
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="glyphicon glyphicon-user"></span> ${user_id }<span class="caret"></span></a>
            	<ul class="dropdown-menu">
            		<li><a href="${pageContext.request.contextPath}/user/logout.do" >
            		<span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
            		<li class="divider"></li>
            		<li><a href="${pageContext.request.contextPath}/user/modifyUserForm.do" >
            		<span class="glyphicon glyphicon-edit"></span> 회원정보수정</a></li>
            		<%-- <li><a href="${pageContext.request.contextPath}/user/deleteUserForm.do" >회원탈퇴</a></li> --%>
            	</ul>
            </li>
          </ul>
        </div>
	</div>
</nav>
