/** 내용 길이 제한 */
$(document).ready(function() {
	$('.reCnt').on('keyup', function() {
		$('#cont_cnt').html("(" + $(this).val().length + " / 700)");

		if ($('.reCnt').val().length > 679) {
			$('.counter').css("color", "#aa0000");
		} else {
			$('.counter').removeAttr("style");
		}
		if ($('.reCnt').val().length > 700) {
			$('.reCnt').val($(this).val().substring(0, 700));
			$('.counter').html("(700 / 700)");
		} else {

		}
	});
});

/**제목 길이 제한*/
$(document).ready(function() {
	$('.reTitle').on('keyup', function() {
		if ($('.reTitle').val().length > 25) {
			$('.reTitle').val($(this).val().substring(0, 25));
		}
	});
});

/** 별점 버튼 */
$(function() {

	if($(":radio[class='form-check-input']").is(':checked')==false){
	$(":radio[class='form-check-input'][value=5]").attr('checked', true);
	} else {
		if($(":radio[class='form-check-input']:checked").val()==1){
		$(".button-class1").text("★");
		$(".button-class2").text("☆");
		$(".button-class3").text("☆");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
		}
		else if($(":radio[class='form-check-input']:checked").val()==2){
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("☆");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
		}
		else if($(":radio[class='form-check-input']:checked").val()==3){
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("★");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
		}
		else if($(":radio[class='form-check-input']:checked").val()==4){
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("★");
		$(".button-class4").text("★");
		$(".button-class5").text("☆");
		}
		else if($(":radio[class='form-check-input']:checked").val()==5){
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("★");
		$(".button-class4").text("★");
		$(".button-class5").text("★");
		}
	}

	$(document).on("click", ".clickck", function() {
		$(":radio[class='form-check-input']").removeAttr('checked');
	});

	$(document).on('click', '.button-class1', function() {
		$(":radio[class='form-check-input'][value=1]").attr('checked', true);
//		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
//		if (!$(this).hasClass('btn-dark')) $(this).addClass('btn-dark');
		$(this).text("★");
		$(".button-class2").text("☆");
		$(".button-class3").text("☆");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
//		if ($('.button-class2').hasClass('btn-dark')) $('.button-class2').removeClass('btn-dark');
//		if (!$('.button-class2').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
//		if ($('.button-class3').hasClass('btn-dark')) $('.button-class3').removeClass('btn-dark');
//		if (!$('.button-class3').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
//		if ($('.button-class4').hasClass('btn-dark')) $('.button-class4').removeClass('btn-dark');
//		if (!$('.button-class4').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
//		if ($('.button-class5').hasClass('btn-dark')) $('.button-class5').removeClass('btn-dark');
//		if (!$('.button-class5').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
	});

	$(document).on('click', '.button-class2', function() {
		$(":radio[class='form-check-input'][value=2]").attr('checked', true);
//		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
//		if (!$(this).hasClass('btn-dark')) $(this).addClass('btn-dark');
		
		$(".button-class1").text("★");
		$(this).text("★");
		$(".button-class3").text("☆");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
		
//		if (!$('.button-class1').hasClass('btn-dark')) $('.button-class1').addClass('btn-dark');
//		if ($('.button-class1').hasClass('btn-default')) $('.button-class1').removeClass('btn-default');

//		if ($('.button-class3').hasClass('btn-dark')) $('.button-class3').removeClass('btn-dark');
//		if (!$('.button-class3').hasClass('btn-default')) $('.button-class3').addClass('btn-default');
//		if ($('.button-class4').hasClass('btn-dark')) $('.button-class4').removeClass('btn-dark');
//		if (!$('.button-class4').hasClass('btn-default')) $('.button-class4').addClass('btn-default');
//		if ($('.button-class5').hasClass('btn-dark')) $('.button-class5').removeClass('btn-dark');
//		if (!$('.button-class5').hasClass('btn-default')) $('.button-class5').addClass('btn-default');
	});

	$(document).on('click', '.button-class3', function() {
		$(":radio[class='form-check-input'][value=3]").attr('checked', true);
//		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
//		if (!$(this).hasClass('btn-dark')) $(this).addClass('btn-dark');
		
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(this).text("★");
		$(".button-class4").text("☆");
		$(".button-class5").text("☆");
		
//		if (!$('.button-class1').hasClass('btn-dark')) $('.button-class1').addClass('btn-dark');
//		if ($('.button-class1').hasClass('btn-default')) $('.button-class1').removeClass('btn-default');
//		if (!$('.button-class2').hasClass('btn-dark')) $('.button-class2').addClass('btn-dark');
//		if ($('.button-class2').hasClass('btn-default')) $('.button-class2').removeClass('btn-default');

//		if ($('.button-class4').hasClass('btn-dark')) $('.button-class4').removeClass('btn-dark');
//		if (!$('.button-class4').hasClass('btn-default')) $('.button-class4').addClass('btn-default');
//		if ($('.button-class5').hasClass('btn-dark')) $('.button-class5').removeClass('btn-dark');
//		if (!$('.button-class5').hasClass('btn-default')) $('.button-class5').addClass('btn-default');
	});

	$(document).on('click', '.button-class4', function() {
		$(":radio[class='form-check-input'][value=4]").attr('checked', true);
//		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
//		if (!$(this).hasClass('btn-dark')) $(this).addClass('btn-dark');
		
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("★");
		$(this).text("★");
		$(".button-class5").text("☆");
		
//		if (!$('.button-class1').hasClass('btn-dark')) $('.button-class1').addClass('btn-dark');
//		if ($('.button-class1').hasClass('btn-default')) $('.button-class1').removeClass('btn-default');
//		if (!$('.button-class2').hasClass('btn-dark')) $('.button-class2').addClass('btn-dark');
//		if ($('.button-class2').hasClass('btn-default')) $('.button-class2').removeClass('btn-default');
//		if (!$('.button-class3').hasClass('btn-dark')) $('.button-class3').addClass('btn-dark');
//		if ($('.button-class3').hasClass('btn-default')) $('.button-class3').removeClass('btn-default');

//		if ($('.button-class5').hasClass('btn-dark')) $('.button-class5').removeClass('btn-dark');
//		if (!$('.button-class5').hasClass('btn-default')) $('.button-class5').addClass('btn-default');
	});

	$(document).on('click', '.button-class5', function() {
		$(":radio[class='form-check-input'][value=5]").attr('checked', true);
//		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
//		if (!$(this).hasClass('btn-dark')) $(this).addClass('btn-dark');
		
		$(".button-class1").text("★");
		$(".button-class2").text("★");
		$(".button-class3").text("★");
		$(".button-class4").text("★");
		$(this).text("★");
		
		
//		if (!$('.button-class1').hasClass('btn-dark')) $('.button-class1').addClass('btn-dark');
//		if ($('.button-class1').hasClass('btn-default')) $('.button-class1').removeClass('btn-default');
//		if (!$('.button-class2').hasClass('btn-dark')) $('.button-class2').addClass('btn-dark');
//		if ($('.button-class2').hasClass('btn-default')) $('.button-class2').removeClass('btn-default');
//		if (!$('.button-class3').hasClass('btn-dark')) $('.button-class3').addClass('btn-dark');
//		if ($('.button-class3').hasClass('btn-default')) $('.button-class3').removeClass('btn-default');
//		if (!$('.button-class4').hasClass('btn-dark')) $('.button-class4').addClass('btn-dark');
//		if ($('.button-class4').hasClass('btn-default')) $('.button-class4').removeClass('btn-default');
	});
});

