$(function() {

	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("#pass2").keyup(function() {
		var pass = $("#pass").val();
		var pass2 = $("#pass2").val();
		if (pass != "" || pass2 != "") {
			if (pass == pass2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#signup").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#signup").attr("disabled", "disabled");
			}
		}
	});
	$("#pass").keyup(function() {
		var pass = $("#pass").val();
		var pass2 = $("#pass2").val();
		if (pass != "" || pass2 != "") {
			if (pass == pass2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#signup").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#signup").attr("disabled", "disabled");
			}
		}
	});
});

$(function() {
	var regExp = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,16}$/;
	$("#alert-success2").hide();
	$("#alert-danger2").hide();
	$("#pass").keyup(function() {
		var pass = $("#pass").val();
		if (pass != "") {
			if (pass.match(regExp) != null) {
				$("#alert-success2").show();
				$("#alert-danger2").hide();
				$("#signup").removeAttr("disabled");
			} else {
				$("#alert-success2").hide();
				$("#alert-danger2").show();
				$("#signup").attr("disabled", "disabled");
			}

		}
	});

});


$(function() {

	$("")

	$(":radio[class='form-check-input'][value=1]").attr('checked', true);

	$('.button-class1').click(function() {
		$(":radio[class='form-check-input'][value=2]").removeAttr('checked');
		$(":radio[class='form-check-input'][value=1]").attr('checked', true);
		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
		if (!$(this).hasClass('btn-primary')) $(this).addClass('btn-primary');
		if ($('.button-class2').hasClass('btn-primary')) $('.button-class2').removeClass('btn-primary');
		if (!$('.button-class2').hasClass('btn-default')) $('.button-class2').addClass('btn-default');
	});

	$('.button-class2').click(function() {
		$(":radio[class='form-check-input'][value=1]").removeAttr('checked');
		$(":radio[class='form-check-input'][value=2]").attr('checked', true);
		if ($(this).hasClass('btn-default')) $(this).removeClass('btn-default');
		if (!$(this).hasClass('btn-primary')) $(this).addClass('btn-primary');
		if ($('.button-class1').hasClass('btn-primary')) $('.button-class1').removeClass('btn-primary');
		if (!$('.button-class1').hasClass('btn-default')) $('.button-class1').addClass('btn-default');
	});

});


let allck = () => {

	var name = $("#name").val();
	var email = $("#email").val();
	var phone = $("#phone").val();
	var gender = $("input[type='radio']:checked").val();
	let gen = "";
	if (gender == 1) {
		gen = "남자";
	} else if (gender == 2) {
		gen = "여자";
	}
	if (name == "") {
		$("#nameck").text("이름 : 입력되지 않음");
	} else {
		$("#nameck").text("이름 : " + name);
	}
	if (email == "") {
		$("#mailck").text("이메일: 입력되지 않음");
	} else {
		$("#mailck").text("이메일: " + email);
	}
	if (phone == "") {
		$("#pnumck").text("전화번호 : 입력되지 않음");
	} else {
		$("#pnumck").text("전화번호 : " + phone);
	}

	if (gen == "") {
		$("#genderck").text("성별 : 입력되지 않음");
	} else {
		$("#genderck").text("성별 : " + gen);
	}
};
