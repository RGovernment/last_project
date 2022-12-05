$(function() {

	$('.main-ck').click(function() {
		let priv = $("input:checkbox[name='p-ck']").is(":checked");
		let term = $("input:checkbox[name='t-ck']").is(":checked");
		if (priv && term) {
			$(".signbutton").removeAttr("disabled");
			$(".allCk").prop('checked', true);
		} else {
			$(".signbutton").attr("disabled", "disabled");
			$(".allCk").prop('checked', false);
		}
	});
});


$(function() {
	$('.allCk').click(function() {
		var checked = $('.allCk').is(':checked');

		if (checked) {
			$('input:checkbox').prop('checked', true);
			$(".signbutton").removeAttr("disabled");
		} else {
			$('input:checkbox').prop('checked', false);
			$(".signbutton").attr("disabled", "disabled");
		}
	});
});