$(document).ready(function() {
	$('.qaCnt').on('keyup', function() {
		$('#cont_cnt').html("(" + $(this).val().length + " / 700)");

		if ($('.qaCnt').val().length > 679) {
			$('.counter').css("color", "#aa0000");
		}else{
			$('.counter').removeAttr("style");
		}
		if ($('.qaCnt').val().length > 700) {
			$('.qaCnt').val($(this).val().substring(0, 700));
			$('.counter').html("(700 / 700)");
		} else {
			
		}
	});
});

$(document).ready(function() {
	$('.qaTitle').on('keyup', function() {
		if ($('.qaTitle').val().length > 25) {
			$('.qaTitle').val($(this).val().substring(0, 25));
		}
	});
});