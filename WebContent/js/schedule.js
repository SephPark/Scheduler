window.onload=function(){
	//일정 등록 유효성 체크
	var sc_form= document.getElementById('schedule_form');
	sc_form.onsubmit=function(){
		var sc_title = document.getElementById('sc_title');
		if(sc_title.value==''){
			alert('일정 제목을 입력하세요!');
			sc_title.focus();
			return false;
		}
		var sc_start = document.getElementById('sc_start');
		if(sc_start.value==''){
			alert('시작 일시를 입력하세요!');
			sc_start.focus();
			return false;
		}
		var sc_end= document.getElementById('sc_end');
		if(sc_end.value==''){
			alert('종료 일시를 입력하세요!');
			sc_end.focus();
			return false;
		}
	};
	var keyword=document.getElementById('keyword');
	
	keyword.keyup=function(){
		return submit;
	}
};









