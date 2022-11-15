Kakao.init('8890a67c089173194979845f9389950d');
console.log(Kakao.isInitialized());

function kakaoLogin() {
	window.Kakao.Auth.login({
		scope: 'profile, gender, age_range', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
		success: function(response) {
			console.log(response) // 로그인 성공하면 받아오는 데이터
			window.Kakao.API.request({ // 사용자 정보 가져오기 
				url: '/v2/user/me',
				success: (res) => {
					const kakao_account = res.kakao_account;
					console.log(kakao_account)
				}
			});
			// window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
		},
		fail: function(error) {
			console.log(error);
		}
	});
}

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
			console.log("end위치  : " + end);
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
});
