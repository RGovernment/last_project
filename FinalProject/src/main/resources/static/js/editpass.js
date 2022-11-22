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
				$("#pass_button").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#pass_button").attr("disabled", "disabled");
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
				$("#pass_button").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#pass_button").attr("disabled", "disabled");
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
				$("#pass_button").removeAttr("disabled");
			} else {
				$("#alert-success2").hide();
				$("#alert-danger2").show();
				$("#pass_button").attr("disabled", "disabled");
			}
		}
	});

});
