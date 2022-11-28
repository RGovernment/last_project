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



function changeToday(e) {
	for (let i = 1; i <= pageYear[first.getMonth()]; i++) {
		if (tdGroup[i].classList.contains('active')) {
			tdGroup[i].classList.remove('active');
		}
	}
	clickedDate1 = e.currentTarget;
	clickedDate1.classList.add('active');
	today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
	var keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
	var today_month = today.getMonth() + 1
	keyValue = today.getFullYear() + '' + today_month + '' + today.getDate();
	console.log(keyValue);
	document.getElementById('test').innerHTML = "<input type='hidden' name='d' value=" + keyValue + ">";
}

$('.member_cnt').change(function() {

	var value = $(this).val();
	console.log(value);
});

$('.option').change(function() {

	var value = $(this).val();
	console.log(value);
});

$('.package').change(function() {

	var value = $(this).val();
	console.log(value);
});


var wday = today;

function getDayOfWeek(wday) {

	const week = ['일', '월', '화', '수', '목', '금', '토'];

	const dayOfWeek = week[new Date(wday).getDay()];

	console.log(dayOfWeek);

	return dayOfWeek;

}

var weekend = today.getFullYear() + '-' + today_month + '-' + today.getDate();
getDayOfWeek(weekend);



var star = document.getElementById("star").value;
console.log(star);

$('#starRating').css('width', star);

//새로고침용 페이지전환
prev();
next();

