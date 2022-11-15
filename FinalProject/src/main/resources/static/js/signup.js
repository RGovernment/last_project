$(function() {
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("#pass2" || "#pass").keyup(function() {
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
