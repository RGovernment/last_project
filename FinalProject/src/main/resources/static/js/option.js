
$(function() {

	$(":radio[class='form-check-input'][value=SINGLE]").attr('checked', true);

	$('.button-class1').click(function() {
		$(":radio[class='form-check-input'][value=PACKAGE]").removeAttr('checked');
		$(":radio[class='form-check-input'][value=SINGLE]").attr('checked', true);
		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
		if (!$(this).hasClass('btn-primary')) $(this).addClass('btn-primary');
		if ($('.button-class2').hasClass('btn-primary')) $('.button-class2').removeClass('btn-primary');
		if (!$('.button-class2').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
	});

	$('.button-class2').click(function() {
		$(":radio[class='form-check-input'][value=SINGLE]").removeAttr('checked');
		$(":radio[class='form-check-input'][value=PACKAGE]").attr('checked', true);
		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
		if (!$(this).hasClass('btn-primary')) $(this).addClass('btn-primary');
		if ($('.button-class1').hasClass('btn-primary')) $('.button-class1').removeClass('btn-primary');
		if (!$('.button-class1').hasClass('btn-default')) $('.button-class1').addClass('btn-default');
	});

});