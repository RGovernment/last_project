var price_num = $("#price_num option:selected").val()
//var price_num = document.getElementById('price_num').value;

var price = 0;
var price1 = document.getElementById('price1').value;
var price2 = document.getElementById('price2').value;
var price3 = document.getElementById('price3').value;
var price4 = document.getElementById('price4').value;
var price5 = document.getElementById('price5').value;
var price6 = document.getElementById('price6').value;

$("#price_num").on("change", function() {
	//selected value
	price_num = $(this).val();
	$("option:selected", this).attr("value");
	//selected option element
	$("option:selected", this);
	$("option:selected", this).text();
	$(this).find("option:selected").text();

	if (price_num == "price1") {
		price = price1;
	} else if (price_num == "price2") {
		price = price2;
	} else if (price_num == "price3") {
		price = price3;
	} else if (price_num == "price4") {
		price = price4;
	} else if (price_num == "price5") {
		price = price5;
	} else if (price_num == "price6") {
		price = price6;
	}
	document.getElementById('price').value = price;
});