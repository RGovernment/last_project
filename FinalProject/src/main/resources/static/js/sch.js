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
var room_id = $('#room_id').val();
if (first.getFullYear() % 4 === 0) {
	pageYear = leapYear;
} else {
	pageYear = notLeapYear;
}

var year = "";
var month = "";

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
				var d1 = "";
				var d2 = "";
				var d3 = "";

				var sd = SDay = 11;

				//시작시간
				var sh = SHour = 12;
				//종료시간
				var eh = EHour = 15;
				//대여시간
				var sch_tt = Math.abs(sh - eh);

				//일이 같으면
				if (cnt == sd) {
					if (sch_tt != null) {
						if (sch_tt == 3) {
							d1 = sh + "~" + eh;
						} else if (sch_tt == 6) {
							d2 = sh + "~" + eh;
						} else if (sch_tt > 6) {
							d3 = sh + "~" + eh;
						}
					}
				}

				//주말 색깔넣기 일요일=빨강 , 토요일=파랑
				var weekend = "";
				if (j == 0) {
					weekend = "red";
				} else if (j == 6) {
					weekend = "blue";
				}
				let scheduledata = "<div id='day_content_date' style='color:" + weekend + "'>" + cnt + "</div>";
				//3H = 초록색 , 6H주황색 , AllDay = 핑크색 

				//주말 색깔넣기 일요일=빨강 , 토요일=파랑
				$td.innerHTML = scheduledata;
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

//prev, next 기능을 합치고 바로 case0을 추가해 바로 title이 나오도록 고침
//기능이 없던 코드 제거
function changemonth(cal) {
	switch (cal) {
		case 0:
			currentTitle.innerHTML = monthList[today.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'
				+ first.getFullYear();
			year = today.getFullYear();
			month = today.getMonth() + +1;
			break;
		case -1:
			if (pageFirst.getMonth() === 1) {
				pageFirst = new Date(first.getFullYear() + cal, 12, 1);
				first = pageFirst;
				if (first.getFullYear() % 4 === 0) {
					pageYear = leapYear;
				} else {
					pageYear = notLeapYear;
				}
			} else {
				pageFirst = new Date(first.getFullYear(), first.getMonth() + cal, 1);
				first = pageFirst;
			}
			today = new Date(today.getFullYear(), today.getMonth() + cal, today.getDate());
			year = today.getFullYear();
			month = today.getMonth() + +1;
			console.log(today.getMonth() + 1);

			currentTitle.innerHTML = monthList[today.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'
				+ first.getFullYear();
			break;
		case 1:
			if (pageFirst.getMonth() === 12) {
				pageFirst = new Date(first.getFullYear() + cal, 1, 1);
				first = pageFirst;
				if (first.getFullYear() % 4 === 0) {
					pageYear = leapYear;
				} else {
					pageYear = notLeapYear;
				}
			} else {
				pageFirst = new Date(first.getFullYear(), first.getMonth() + cal, 1);
				first = pageFirst;
			}

			today = new Date(today.getFullYear(), today.getMonth() + cal, today.getDate());
			year = today.getFullYear();
			month = today.getMonth() + 1;
			currentTitle.innerHTML = monthList[today.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;' + first.getFullYear();
			break;
	}
	removeCalendar();
	showCalendar();
	clickStart();
	month_sch()

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
		month = today.getMonth() + 1;
		console.log(today.getMonth() + 1);
		document.getElementById('current-year-month').innerHTML = monthList[today.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;' + first.getFullYear();
		removeCalendar();
		showCalendar();
		clickedDate1 = document.getElementById(today.getDate());
		clickedDate1.classList.add('active');
		clickStart();
		month_sch();
	}

	//달력 꾸미기 : 화살표 버튼과 년도(숫자),월(영문)
	var clickedDate1 = document.getElementById(today.getDate());
	clickedDate1.classList.add('active');
	var prevBtn = document.getElementById('prev');
	var nextBtn = document.getElementById('next');
	$('#prev').click(function() { changemonth(-1) });
	$('#next').click(function() { changemonth(1) });
	var tdGroup = [];

	//이번달에 이벤트 활성화
	function clickStart() {
		for (let i = 1; i <= pageYear[first.getMonth()]; i++) {
			tdGroup[i] = document.getElementById(i);
			tdGroup[i].addEventListener('click', changeToday);
		}
	}

	var selectedDate;
	function changeToday(e) {
		for (let i = 1; i <= pageYear[first.getMonth()]; i++) {

			if (tdGroup[i].classList.contains('active')) {
				tdGroup[i].classList.remove('active');
				clickedDate1.classList.remove("back");
			}
		}
		clickedDate1 = e.currentTarget;

		//날짜 선택 시 예약 가능 시간만 선택할 수 있게 변환하기
		var dayschedules = clickedDate1.children;
		for (let dayschedulesindex = 1; dayschedulesindex < dayschedules.length; dayschedulesindex++) {
			var tempStime = dayschedules[dayschedulesindex].dataset.starttime
			var tempEtime = dayschedules[dayschedulesindex].dataset.endtime
			for (let k = tempStime - 3; k <= tempEtime; k++) {
				console.log($('.S#T3').find('[value=' + k + ']'));
				$('.S#T3').find('[value=' + k + ']').attr("disabled", true);
			}
			for (let k = tempStime - 6; k <= tempEtime; k++) {
				console.log($('.S#T6').find('[value=' + k + ']'));
				$('.S#T6').find('[value=' + k + ']').attr("disabled", true);
			}
			for (let k = tempStime; k <= tempEtime; k++) {
				console.log($('.S#TA').find('[value=' + k + ']'));
				$('.S#TA').find('[value=' + k + ']').attr("disabled", true);
			}
		}

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
		$(".S").removeAttr("hidden");

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
	var xhr = new XMLHttpRequest();
	var csrf_token = $('[name=csrfmiddlewaretoken]').val();
	xhr.open("POST", "요청 주소", true);
	xhr.setRequestHeader('X-CSRFToken', csrf_token);


	//영수증 출력
	$('.add_option').click(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var optprice_id = $('#selectoption').val();
		$.ajax({
			type: "POST",
			url: "/res/optprice",
			beforeSend: function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
			},
			data: { optprice_id: optprice_id },
			async: true, //동기, 비동기 여부
			cache: false, // 캐시 여부
			success: function(result) {
				console.log(result);
				printoptionlist(result);
			},
			error: function() {
			}
		}); //$.ajax
	});


	//해당 달의 예약 현황을 가져오는 메소드
	function month_sch() {
		var year_id = year;
		var month_id = month;
		var room_id_ob = room_id;
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		let object = {
			"year_id": year_id,
			"month_id": month_id,
			"room_id_ob": room_id_ob,
		};
		$.ajax({
			type: "POST",
			url: "/res/getreservedata",
			dataType: 'json',
			data: object,
			beforeSend: function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
			},
			success: function(reserve) {
				console.log(reserve);
				addsch(reserve);
			},
			error: function() {
				alert('에러');
			}
		}); //$.ajax
	};
	// 예약 현황을 달력에 뿌려주는 메소드
	function addsch(reserve) {
		if (reserve != null) {
			for (var reserveindex = 0; reserveindex < reserve.length; reserveindex++) {
				let tdid = Number(reserve[reserveindex].SDay)
				$("#" + tdid).append("<div id='day_content1' data-StartTime ='" + reserve[reserveindex].SHour + "'data-EndTime ='" + reserve[reserveindex].EHour + "'>" + reserve[reserveindex].SHour + " - " + reserve[reserveindex].EHour + "</div>");
			}
		}
	}

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
		$("#printtotalprice").html(totalprice + "원");
		$("#totalprice").val(totalprice);
		$("#options").val(optionsid);
	};

	let clearAll = () => {
		$("#printoptions").text("");
		$("#printtotalprice").text("");
		$("#options").val("");
		Optionprice = 0;
		$(".optbtn").attr("disabled", "disabled");
		$(".S").attr("hidden", "hidden");
		$(".RentTime").attr("disabled", "disabled");
		$(".chan-text").text("날짜 선택(달력에서 날짜를 선택해주세요.)");
		$(".chan-text2").text("옵션(날짜를 먼저 선택해주세요.)");

	}

	//영수증 출력
	$(function() {
		$('.S').change(function() {
			printoptionlist2();
		});
	});


	function printoptionlist2() {

		totalprice = Roomprice;
		$("#printtotalprice").html(totalprice + "원");
		$("#totalprice").val(totalprice);
	};


	var star = document.getElementById("star").value;
	console.log(star);

	$('#starRating').css('width', star);

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
	let nullck = () => {
		if (!$.trim($('#printtotalprice').html()).length) {
			return false;
		}
		return true;
	}
}

changemonth(0);
