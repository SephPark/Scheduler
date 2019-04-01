$(document).ready(function(){
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val()==''){
			alert('검색어를 입력하세요');
			$('#keyword').focus();
			return false;
		}
	});
	//글 등록, 수정 유효성 체크
	$('#write_form,#update_form').submit(function(){
		if($('#d_title').val()==''){
			alert('제목을 입력하세요!');
			$('#d_title').focus();
			return false;
		}
		if($('#d_content').val()==''){
			alert('내용을 입력하세요!');
			$('#d_content').focus();
			return false;
		}
	});
});