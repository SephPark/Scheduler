<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 업로드</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gallery.js"></script>
</head>
<style type="text/css">
    .img_wrap {
        width: 300px;
        margin-top: 50px;
    }
    .img_wrap img {
        max-width: 100%;
    }
    
</style>
<script type="text/javascript">
var sel_file;

$(document).ready(function() {
	$("#g_photo1").on("change", handleImgFileSelect);
	var g_photo1= document.getElementById('g_photo1');
	
}); 

function handleImgFileSelect(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function(f) {
        if(!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }

        sel_file = f;

        var reader = new FileReader();
        reader.onload = function(e) {
        	$('#img').parent().show();
            $("#img").attr("src", e.target.result);
        }
        reader.readAsDataURL(f);
    });
    
    function sendFile(file,el){
 	   var form_data = new FormData();
 	   form_data.append('file',file);
 	   
 	   //이미지 전송
 	   $.ajax({
 		   url:'imageUploader.do',
 		   type:'post',
 		   data:form_data,
 		   enctype:'multipart/form-data',
 		   cache:false,
 		   contentType:false,
 		   processData:false,
 		   success:function(img_name){
 			   $(el).summernote('editor.insertImage',img_name);
 		   }
 		   
 	   });
 	   
    } 
}
</script>

<body>
<div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
	<form action="uploadGallery.do" method="post" id="gallery_form" enctype="multipart/form-data">
		<br><br><br>
		<h1 class="text-center">갤러리</h1>
		<br>
		
		<label class="sr-only">파일 업로드</label>
		<input type="file" name="g_photo1" id="g_photo1">
		<br>
		
		
		<div class="thumbnail" style="display:none">
			<img id="img">
		</div> 
		
		<label class="sr-only">제목</label>
		<input type="text" name="g_title" id="g_title" placeholder="제목" 
		class="form-control" autocomplete="off">
		<br>
		
		<label class="sr-only">내용</label>
		<textarea rows="7" name="g_content" class="form-control" 
		style="resize:none;" placeholder="내용"></textarea>
		<br>
		
		
		<br>
		<div class="align-right">
			<input type="submit" class="btn btn-primary" value="저장">
			<button type="button" class="btn btn-default"
			onclick="location.href='${pageContext.request.contextPath}/gallery/listGallery.do'">취소</button>
		</div>
	</form>
</div>

</body>
</html>




