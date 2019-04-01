window.onload=function(){
	//일정 등록 유효성 체크
	var g_form= document.getElementById('gallery_form');
	
	g_form.onsubmit=function(){
		var g_title = document.getElementById('g_title');
		if(g_title.value==''){
			alert('갤러리 제목을 입력하세요!');
			g_title.focus();
			return false;
		}
		
		var g_photo1 = document.getElementById('g_photo1');
		if(g_photo1.value==''){
			alert('사진을 업로드 하세요!');
			return false;
		}
	};
};









