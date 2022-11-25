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
				$td.textContent = cnt;
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
	inputBox.value = "";
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
	currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'
		+ first.getFullYear();
	removeCalendar();
	showCalendar();
	showMain();
	clickedDate1 = document.getElementById(today.getDate());
	clickedDate1.classList.add('active');
	clickStart();
	reshowingList();
}

function next() {
	inputBox.value = "";
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
	currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'
		+ first.getFullYear();
	removeCalendar();
	showCalendar();
	showMain();
	clickedDate1 = document.getElementById(today.getDate());
	clickedDate1.classList.add('active');
	clickStart();
	reshowingList();
}

//달력 꾸미기 : 화살표 버튼과 년도(숫자),월(영문)
//메인(todolist) 보여주기
function showMain() {
	var mainTodayDay = document.getElementById('main-day');
	var mainTodayDate = document.getElementById('main-date');

	mainTodayDay.innerHTML = dayList[today.getDay()];
	mainTodayDate.innerHTML = today.getDate();
}

var clickedDate1 = document.getElementById(today.getDate());
clickedDate1.classList.add('active');
var prevBtn = document.getElementById('prev');
var nextBtn = document.getElementById('next');
prevBtn.addEventListener('click', prev);
nextBtn.addEventListener('click', next);
var tdGroup = [];

function clickStart() {
	for (let i = 1; i <= pageYear[first.getMonth()]; i++) {
		tdGroup[i] = document.getElementById(i);
		tdGroup[i].addEventListener('click', changeToday);
	}
}

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
	showMain();
	keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();

	reshowingList();
}

//동작후 달력 다시 그리기
function reshowingList() {
	keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
	//기존값지우기
	if (todoList[keyValue] === undefined) {
		inputList.textContent = '';
		todoList[keyValue] = [];
		const $divs = document.querySelectorAll('#input-list > div');
		$divs.forEach(function(e) { e.remove(); });
		const $btns = document.querySelectorAll('#input-list > button');
		$btns.forEach(function(e1) { e1.remove(); });
	} else if (todoList[keyValue].length === 0) {
		inputList.textContent = "";
		const $divs = document.querySelectorAll('#input-list > div');
		$divs.forEach(function(e) { e.remove(); });
		const $btns = document.querySelectorAll('#input-list > button');
		$btns.forEach(function(e1) { e1.remove(); });
	} else {
		const $divs = document.querySelectorAll('#input-list > div');
		$divs.forEach(function(e) { e.remove(); });
		const $btns = document.querySelectorAll('#input-list > button');
		$btns.forEach(function(e1) { e1.remove(); });

		//여기부터 todo list 재생성

		var $div = document.createElement('div');
		for (var i = 0; i < todoList[keyValue].length; i++) {
			var $div = document.createElement('div');
			$div.textContent = '-' + todoList[keyValue][i];

			/*관리자용 삭제버튼만들기
			var $btn = document.createElement('button');
			$btn.setAttribute('type','button');
			$btn.setAttribute('id','del-ata');
			$btn.setAttribute('id',dataCnt+ keyValue);
			$btn.setAttribute('class','del-data');
			$btn.textContent = delText;
			 */

			inputList.appendChild($div);

			/*관리자용
			inputList.appendChild($btn);
			$div.addEventListener('click',checkList);
			$btn.addEventListener('click',deleteTodo);
			inputBox.value = '';
			function deleteTodo() {
			$div.remove();
			$btn.remove();
			}
			*/
		}
	}
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

var weekend = today.getFullYear() + '-' + today_month + '-' + today.getDate();
getDayOfWeek(weekend);
var wday = today;


function getDayOfWeek(wday) {

	const week = ['일', '월', '화', '수', '목', '금', '토'];

	const dayOfWeek = week[new Date(wday).getDay()];

	console.log(dayOfWeek);

	return dayOfWeek;

}

//입력상자
var inputBox = document.getElementById('input-box');
//input 버튼
var inputDate = document.getElementById('input-data');
var inputList = document.getElementById('input-list');
var delText = 'X';
inputDate.addEventListener('click', addTodoList);
var dataCnt = 1;
keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
//리스트출력용 배열생성후 값 직접 집어넣기
var todoList = [];
todoList[keyValue] = [];
todoList[keyValue].push("미니룸A", "미니룸B", "파티룸A", "파티룸B");

function addTodoList() {
	var $div = document.createElement('div');
	$div.textContent = '-' + inputBox.value;

	/*관리자용 삭제버튼 만들기
	var $btn = document.createElement('button');
	$btn.setAttribute('type','button');
	$btn.setAttribute('id','del-ata');
	$btn.setAttribute('id',dataCnt+ keyValue);
	$btn.setAttribute('class',"del-data");
	$btn.textContent = delText;
	*/

	inputList.appendChild($div);

	/*버튼 만들기
	inputList.appendChild($btn);
	 */
	todoList[keyValue].push(inputBox.value);
	dataCnt++;
	//입력값 초기화
	inputBox.value = '';

	/*리스트 (체크,삭제) 이벤트 , 삭제 함수
	$div.addEventListener('click',checkList);
	$btn.addEventListener('click',deleteTodo);
	function deleteTodo() {
		$div.remove();
		$btn.remove();
	}
	*/
}
console.log(keyValue);

/*리스트 체크 함수
function checkList(e) {
	e.currentTarget.classList.add('checked');
}
*/

//새로고침용 페이지전환
prev();
next();