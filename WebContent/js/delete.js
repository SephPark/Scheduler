window.onload=function(){
	//회원탈퇴 유효성 체크
	var form = document.getElementById('delete_form');
	form.onsubmit=function(){
		var id = document.getElementById('id');
		if(id.value==''){
			alert('아이디를 입력하세요!');
			id.focus();
			return false;
		}
		var pwd = document.getElementById('pwd');
		if(pwd.value==''){
			alert('비밀번호를 입력하세요!');
			pwd.focus();
			return false;
		}
		var cpwd = document.getElementById('cpwd');
		if(cpwd.value==''){
			alert('비밀번호 확인을 입력하세요!');
			cpwd.focus();
			return false;
		}
		if(pwd.value!=cpwd.value){
			alert('비밀번호와 비밀번호 확인이 불일치합니다.');
			cpwd.value='';
			cpwd.focus();
			return false;
		}
	};
};



