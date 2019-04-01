window.onload=function(){
	//회원정보 수정 유효성 체크
	var form = document.getElementById('modify_form');
	form.onsubmit=function(){
		var name = document.getElementById('name');
		if(name.value==''){
			alert('이름을 입력하세요!');
			name.focus();
			return false;
		}
		var pwd = document.getElementById('pwd');
		if(pwd.value==''){
			alert('비밀번호를 입력하세요!');
			passwd.focus();
			return false;
		}
		var phone = document.getElementById('phone');
		if(phone.value==''){
			alert('전화번호를 입력하세요!');
			phone.focus();
			return false;
		}
		var email = document.getElementById('email');
		if(email.value==''){
			alert('이메일을 입력하세요!');
			email.focus();
			return false;
		}
	};
};









