window.onload=function(){
	//로그인 유효성 체크
	var loginForm = document.getElementById('login_form');
	loginForm.onsubmit=function(){
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
	};
};