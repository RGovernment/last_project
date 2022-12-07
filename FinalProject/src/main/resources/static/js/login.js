/*  $(function(){
	$("#btn-kakao-login").click(function(event){
		// a태그 기능 실행멈춤.
		event.preventDefault();
		// 카카오 로그인 실행시 오류메시지를 표시하는 경고창을 화면에 보이지 않게 한다.
		$("alert-kakao-login").addClass("d-none");
		// 사용자 키를 전달, 카카오 로그인 서비스 초기화.
		Kakao.init('78a3092d5af53803282c4c49afd6a7a9');
		// 카카오 로그인 서비스 실행하기 및 사용자 정보 가져오기.
		Kakao.Auth.login({
			success:function(auth){
				Kakao.API.request({
					url: '/v2/user/me',
					success: function(response){
						// 사용자 정보를 가져와서 폼에 추가.
						var account = response.kakao_account;				
						$('#form-kakao-login input[name=email]').val(account.email);
						$('#form-kakao-login input[name=name]').val(account.profile.nickname);
						// 사용자 정보가 포함된 폼을 서버로 제출한다.
						document.querySelector('#form-kakao-login').submit();
					},
					fail: function(error){
						// 경고창에 에러메시지 표시
						$('alert-kakao-login').removeClass("d-none").text("카카오 로그인 처리 중 오류가 발생했습니다.")
					}
				}); // api request
			}, // success 결과.
			fail:function(error){
				// 경고창에 에러메시지 표시
				$('alert-kakao-login').removeClass("d-none").text("카카오 로그인 처리 중 오류가 발생했습니다.")
			}
		}); // 로그인 인증.
	}) // 클릭이벤트
})// 카카오로그인 끝.

*/

$(function () {
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
});


$(document).ready(function() {
	// 저장된 쿠키값 > ID
	var key = getCookie("key");
	$("#floatingInput").val(key);

	if ($("#floatingInput").val() != "") {
		$(".ckbox").attr("checked", true);
	}

	$(".ckbox").change(function() {
		if ($(".ckbox").is(":checked")) {
			setCookie("key", $("#floatingInput").val(), 7);
		} else {
			deleteCookie("key");
		}
	});

	// ID 저장하기 체크 후 ID를 입력하는 경우에도 쿠키 저장
	$("#floatingInput").keyup(function() {
		if ($(".ckbox").is(":checked")) {
			setCookie("key", $("#floatingInput").val(), 7);
		}
	});

	// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	// 쿠키 삭제
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
	}

	// 쿠키 가져오기
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) { // 쿠키가 존재하면
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정 
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
});
