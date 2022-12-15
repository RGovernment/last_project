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
