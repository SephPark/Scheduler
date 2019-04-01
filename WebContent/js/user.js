$(document).ready(function(){
	var idChecked = 0;
	
	//아이디 중복 체크
	$('#id_check').click(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
			$('#id').focus();
			return;
		}
		$('#message_id').text('');//메시지 초기화
		//$('#loading').show(); //로딩 이미지 노출
		
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//$('#loading').hide();//로딩 이미지 감추기
				
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','black')
					               .text('등록 가능 ID');
					idChecked = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red')
		                            .text('중복된 ID');
					$('#id').val('').focus();
					idChecked = 0;
				}else{
					alert('아이디 중복 체크 오류 발생');
				}				
			},
			error:function(){
				//$('#loading').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});
	//이메일 중복 체크 
	var emailChecked=0;
	$('#email_check').click(function(){
		if ($('#email').val()=='') {
			alert('이메일을 입력하세요!');
			$('#email').focus();
			return;
		}
		$('#message_id').text('');//메시지 초기화
		$.ajax({
			url:'checkDuplicatedEmail.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			cach:false,
			timeout:30000,
			success:function(data){
				if (data.result=='emailNotFound') {
					$('#message_email').css('color','black').text('사용 가능 이메일');
					emailChecked=1;
				}else if(data.result=='emailDuplicated'){
					$('#message_email').css('color','red').text('중복된 이메일');
					$('#email').val('').focus();
					emailChecked=0;
				}else{
					alert('email중복 체크 중 오류 발생!!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!!!');
			}
		});
	});
	
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		idChecked=0;
		$('#message_id').text('');
	});
	$('#register_form #email').keydown(function(){
		emailChecked=0;
		$('#message_email').text('');
	});
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
			$('#id').focus();
			return false;
		}
		if(idChecked==0){
			alert('아이디 중복 체크 필수!');
			$('#id').focus();
			return false;
		}
		if($('#name').val()==''){
			alert('이름을 입력하세요!');
			$('#name').focus();
			return false;
		}
		if($('#passwd').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').focus();
			return false;
		}
		if($('#phone').val()==''){
			alert('전화번호를 입력하세요!');
			$('#phone').focus();
			return false;
		}
		if($('#email').val()==''){
			alert('이메일을 입력하세요!');
			$('#email').focus();
			return false;
		}
	});
});





