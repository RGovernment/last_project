//달력생성
var currentTitle = document.getElementById('current-year-month');
var calendarBody = document.getElementById('calendar-body');
var today = new Date();
var first = new Date(today.getFullYear(), today.getMonth(), 1);
var dayList = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var monthList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var notLeapYear = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var pageFirst = first;
var pageYear;
var delprice = 0;

if (first.getFullYear() % 4 === 0) {
	pageYear = leapYear;
} else {
	pageYear = notLeapYear;
}

function showCalendar() {
	let monthCnt = 100;
	let cnt = 1;
	for (var i = 0; i < 6; i++) {
		var $tr = document.createElement('tr');
		$tr.setAttribute('id', monthCnt);
		for (var j = 0; j < 7; j++) {
			if ((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]) {
				var $td = document.createElement('td');
				$tr.appendChild($td);
			} else {
				var $td = document.createElement('td');

				//더미데이터 변수
				var o = "예약중";
				var x = "예약가능";
				var d1 = "";
				var d2 = "";
				//더미생성
				if (j % 2 == 0) {
					d1 = o;
					d2 = x;
				} else {
					d1 = x;
					d2 = o;
				}

				//주말 색깔넣기
				var weekend = "";
				if (j == 0) {
					weekend = "red";
				} else if (j == 6) {
					weekend = "blue";
				}

				$td.innerHTML = "<div id='day_content_date' style='color:" + weekend + "'>" + cnt + "</div>" +
					"<div id='day_content1'>" + d1 + "</div>" + "<div id='day_content2'>" + d2 + "</div>" + "<div id='day_content3'>" + d2 + "</div>";

				$td.setAttribute('id', cnt);
				$tr.appendChild($td);
				cnt++;
			}
		}
		monthCnt++;
		calendarBody.appendChild($tr);
	}
}
showCalendar();

function removeCalendar() {
	let catchTr = 100;
	for (var i = 100; i < 106; i++) {
		var $tr = document.getElementById(catchTr);
		$tr.remove();
		catchTr++;
	}
}

//prev , next
function prev() {
	//inputBox.value = "";
	const $divs = document.querySelectorAll('#input-list > div');
	$divs.forEach(function(e) { e.remove(); });
	const $btns = document.querySelectorAll('#input-list > button');
	$btns.forEach(function(e1) { e1.remove(); });
	if (pageFirst.getMonth() === 1) {
		pageFirst = new Date(first.getFullYear() - 1, 12, 1);
		first = pageFirst;
		if (first.getFullYear() % 4 === 0) {
			pageYear = leapYear;
		} else {
			pageYear = notLeapYear;
		}
	} else {
		pageFirst = new Date(first.getFullYear(), first.getMonth() - 1, 1);
		first = pageFirst;
	}
	today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	currentTitle.innerHTML = monthList[today.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'
		+ first.getFullYear();
	removeCalendar();
	showCalendar();
	clickedDate1 = document.getElementById(today.getDate());
	clickedDate1.classList.add('active');
	clickStart();
}

function next() {
	//inputBox.value = "";
	const $divs = document.querySelectorAll('#input-list > div');
	$divs.forEach(function(e) { e.remove(); });
	const $btns = document.querySelectorAll('#input-list > button');
	$btns.forEach(function(e1) { e1.remove(); });
	if (pageFirst.getMonth() === 12) {
		pageFirst = new Date(first.getFullYear() + 1, 1, 1);
		first = pageFirst;
		if (first.getFullYear() % 4 === 0) {
			pageYear = leapYear;
		} else {
			pageYear = notLeapYear;
		}
	} else {
		pageFirst = new Date(first.getFullYear(), first.getMonth() + 1, 1);
		first = pageFirst;
	}

	today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
	document.getElementById('current-year-month').innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;' + first.getFullYear();
	removeCalendar();
	showCalendar();
	clickedDate1 = document.getElementById(today.getDate());
	clickedDate1.classList.add('active');
	clickStart();
}

//달력 꾸미기 : 화살표 버튼과 년도(숫자),월(영문)
var clickedDate1 = document.getElementById(today.getDate());
clickedDate1.classList.add('active');
var prevBtn = document.getElementById('prev');
var nextBtn = document.getElementById('next');
prevBtn.addEventListener('click', prev);
nextBtn.addEventListener('click', next);
var tdGroup = [];

//이번달에 이벤트 활성화
function clickStart() {
	for (let i = 1; i <= pageYear[first.getMonth()]; i++) {
		tdGroup[i] = document.getElementById(i);
		tdGroup[i].addEventListener('click', changeToday);
	}
}
//
var selectedDate;
function changeToday(e) {
	for (let i = 1; i <= pageYear[first.getMonth()]; i++) {
		
		if (tdGroup[i].classList.contains('active')) {
			tdGroup[i].classList.remove('active');
			clickedDate1.classList.remove("back");
		}
	}
	clickedDate1 = e.currentTarget;
	$(".chan-text").text("날짜 선택");
	$('.RentTime').removeAttr("disabled");
	clickedDate1.classList.add('active');
	clickedDate1.classList.add('back');
	today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
	var today_month = today.getMonth() + 1
	selectedDate = today.getFullYear() + '-' + today_month + '-' + today.getDate();
	console.log(selectedDate);
	
	$('.RentTime').find("option:eq(0)").prop("selected", true);
	$('.S').css("display", "none");
	$("#start_time").val(null);
	$("#end_time").val(null);
}

var optionsid = "";
var totalprice = 0;
var Roomprice = 0;
var Optionprice = 0;
var optid = 0;
//대여 시간 선택
$('.RentTime').change(function() {
	var Rtime = $(this).val();
	var Rprice = $(this).find("option:selected").data("price");
	if (Rtime == 3) {
		$('#T6').css("display", "none");
		$('#TA').css("display", "none");
		$('#T3').removeAttr("style");
		$('#T3').css("border-radius", "5px");
	} else if (Rtime == 6) {
		$('#T3').css("display", "none");
		$('#TA').css("display", "none");
		$('#T6').removeAttr("style");
		$('#T6').css("border-radius", "5px");
	} else if (Rtime == 15) {
		$('#T3').css("display", "none");
		$('#T6').css("display", "none");
		$('#TA').removeAttr("style");
		$('#TA').css("border-radius", "5px");
	}
	$('#T3').find("option:eq(0)").prop("selected", true);
	$('#T6').find("option:eq(0)").prop("selected", true);
	$('#TA').find("option:eq(0)").prop("selected", true);
	Roomprice = Rprice;
	totalprice = Optionprice + Roomprice;
	$("#totalprice").val(totalprice);

});
// 대여 시작 시간선택
$('.S').change(function() {
	var Stime = $(this).val();
	var RentTime = $('.RentTime').val();
	var Etime = parseInt(Stime) + parseInt(RentTime);
	var Sresult = selectedDate + " " + Stime + ":00:00";
	$("#start_time").val(Sresult);
	var Eresult = "";
	const tomorrow = new Date(today);
	tomorrow.setDate(today.getDate() + 1);
	if (Etime == 24) {
		Eresult = tomorrow.getFullYear() + '-' + (tomorrow.getMonth() + 1) + '-' + tomorrow.getDate() + " " + "00:00:00";
	} else {
		Eresult = selectedDate + " " + Etime + ":00:00";
	}
	if (RentTime == 15) {
		Eresult = tomorrow.getFullYear() + '-' + (tomorrow.getMonth() + 1) + '-' + tomorrow.getDate() + " " + "09:00:00";
	}
	$("#end_time").val(Eresult);
});

//영수증 출력
$('.add_option').click(function() {
	var optprice_id = $('#selectoption').val();
	$.ajax({
		type: "GET",
		url: "/res/optprice",
		data: { optprice_id: optprice_id },
		success: function(result) {
			printoptionlist(result);
		},
		error: function() {
		}

	}); //$.ajax
});





function printoptionlist(optprice) {
	var divArea = $("#printoptions");

	var str = "";

	str += "<div class='justify-content-middle g-0 mb-2' id=" + optid 
	+ " style='border:1px solid black;border-radius:5px; background-color:#7d7d7f; color:white;'>";
	
	str += optprice.content + "&nbsp" + "<div class='my-0' id=" + optid 
	+ "price style='background-color:#ffffff;color:#000000;border-bottom-left-radius:5px;border-bottom-right-radius:5px;'>" + optprice.price + "원<div>";
	
	str += "</div>";
	divArea.append(str);
	optid += 1;
	Optionprice += optprice.price;
	if (optionsid == "") {
		optionsid += optprice.id;
	} else {
		optionsid += ",";
		optionsid += optprice.id;
	}
	totalprice = Optionprice + Roomprice;
	$("#printtotalprice").html(totalprice +"원");
	$("#totalprice").val(totalprice);
	$("#options").val(optionsid);
};

let clearAll = () => {
	$("#printoptions").text("");
	$("#printtotalprice").text("");
	$("#options").val("");
	Optionprice = 0;
	$(".optbtn").attr("disabled","disabled");
	$(".S").attr("hidden","hidden");
	$(".RentTime").attr("disabled","disabled");
	$(".chan-text").text("날짜 선택(달력에서 날짜를 선택해주세요.)");
	$(".chan-text2").text("옵션(날짜를 먼저 선택해주세요.)");
	//optbtn disabled;
	//class s hidden;
	//RenTime disabled;

}
/*
function deletediv(optid) {
	delprice = $("#" + optid + "price").val();
	totalprice -= delprice;
	$("#totalprice").val(totalprice);
	$("#printtotalprice").html("금액 :" + totalprice);
	$("#" + optid).remove();
};*/

//영수증 출력
$('.S').change(function() {
	var optprice_id = $('#selectoption').val();
	$.ajax({
		type: "GET",
		url: "/res/optprice",
		data: { optprice_id: optprice_id },
		success: function(result) {
			printoptionlist2(result);
		},
		error: function() {
		}

	}); //$.ajax
});


function printoptionlist2(optprice) {

	optid += 1;
	Optionprice += optprice.price;
	if (optionsid == "") {
		optionsid += optprice.id;
	} else {
		optionsid += ",";
		optionsid += optprice.id;
	}
	totalprice = Roomprice;
	$("#printtotalprice").html("금액 :" + totalprice);
	$("#totalprice").val(totalprice);
	$("#options").val(optionsid);
};



$(function() {
	$(".S").change(function() {

		if ($(".S").val() != "") {
			$(".optbtn").removeAttr("disabled");
			$(".chan-text2").text("옵션");
		} else {
			$(".optbtn").attr("disabled", "disabled");
		}


	});
});

//* 최종 가격 널 체크, totalprice 미 존재시 버튼 비활성화 */
let nullck = () =>{
	if( !$.trim( $('#printtotalprice').html()).length ) {
	return false;
	}
return true;
}

//var star = document.getElementById("star").value;
//console.log(star);

//$('#starRating').css('width', star);

//새로고침용 페이지전환

/*
	var sel = "";
	sel += "<select class='S'>";
	for(var i = 0 ;i<24;i++){
		sel += "<option value="+i+">"+i+"</option>"
	}
	sel += "</select>";
		document.getElementById('Stime').innerHTML=sel;
	*/
prev();
next();

