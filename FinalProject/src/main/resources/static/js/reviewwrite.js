$(document).ready(function() {
	$('.reCnt').on('keyup', function() {
		$('#cont_cnt').html("(" + $(this).val().length + " / 700)");

		if ($('.reCnt').val().length > 679) {
			$('.counter').css("color", "#aa0000");
		}else{
			$('.counter').removeAttr("style");
		}
		if ($('.reCnt').val().length > 700) {
			$('.reCnt').val($(this).val().substring(0, 700));
			$('.counter').html("(700 / 700)");
		} else {
			
		}
	});
});

$(document).ready(function() {
	$('.reTitle').on('keyup', function() {
		if ($('.reTitle').val().length > 25) {
			$('.reTitle').val($(this).val().substring(0, 25));
		}
	});
});