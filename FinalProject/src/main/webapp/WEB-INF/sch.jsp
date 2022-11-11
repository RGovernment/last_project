<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="resources/css/sch.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<title>스케쥴러</title>
<script>
var currentTitle = document.getElementById('current-year-month');
var calendarBody = document.getElementById('calendar-body');
var today = new Date();
var first = new Date(today.getFullYear(), today.getMonth(),1);
var dayList = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
var monthList = ['January','February','March','April','May','June','July','August','September','October','November','December'];
var leapYear=[31,29,31,30,31,30,31,31,30,31,30,31];
var notLeapYear=[31,28,31,30,31,30,31,31,30,31,30,31];
var pageFirst = first;
var pageYear;
if(first.getFullYear() % 4 === 0){
    pageYear = leapYear;
}else{
    pageYear = notLeapYear;
}

function showCalendar(){
    let monthCnt = 100;
    let cnt = 1;
    for(var i = 0; i < 6; i++){
        var $tr = document.createElement('tr');
        $tr.setAttribute('id', monthCnt);   
        for(var j = 0; j < 7; j++){
            if((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]){
                var $td = document.createElement('td');
                $tr.appendChild($td);     
            }else{
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

function removeCalendar(){
    let catchTr = 100;
    for(var i = 100; i< 106; i++){
        var $tr = document.getElementById(catchTr);
        $tr.remove();
        catchTr++;
    }
}
/*
	//변수선언
	var currentTitle = document.getElementById('current-year-month');
	var calendarBody = document.gerElementById('calendar-body');
	//오늘
	var today = new Date();
	//첫날
	var first = new Date(today.getFullYear(), today.getMonth(), 1);
	//요일
	var dayList = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
			'Friday', 'Saturday' ];
	//월
	var monthList = [ 'January', 'February', 'March', 'April', 'May', 'June',
			'July', 'August', 'September', 'October', 'November', 'December' ];
	//윤년 월별 말일
	var leapYear = [ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	//일반 월별 말일
	var notLeapYear = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	//첫번째 페이지
	var pageFirst = first;
	//년도 페이지
	var pageYear;

	//윤년 체크 
	if (first.getFullYear() % 4 === 0) {
		pageYear = leapYear;
	} else {
		pageYear = notLeapYear;
	}

	//달력을 보여주는 함수
	function showCalendar() {
		let monthCnt = 100;
		let cnt = 1;
		//최대 6주계산
		for (var i = 0; i < 6; i++) {
			//<tr>태그를 만들어주는것
			var $tr = document.createElement('tr');
			$tr.setAttribute('id', monthCnt);
			//7일 계산
			for (var j = 0; j < 7; j++) {
				//
				if ((i === 0 && j < first.getDay())
						|| cnt > pageYear[first.getMonth()]) {
					//요일 칸 생성 <td>태그로 한칸씩
					var $td = document.createElment('td');
					$tr.appendChild($td);
				} else {
					//앞에 1을 붙혀줌 ex)11 ,12
					var $td = document.createElement('td');
					$td.textContent = cnt;
					$td.setAttribute('id', cnt);
					$tr.appendChild($td);
					cnt++;
				}
			}
			monthCnt++;
			//한주가 끝났으므로 줄바꿈
			calendarBody.appendChild($tr);
		}
	}

	//위에서 만든 함수 호출
	showCalendar();
	//추후 달력 업데이트를 위함
	function removeCalendar() {
		let catchTr = 100;
		for (var i = 100; i < 106; i++) {
			var$tr = document.getElementById(catchTr);
			$tr.remove();
			catchTr++;
		}
	}
	*/

	//이전 달로 이동하는 함수
	function prev() {
		inputBox.value = "";
		const $divs = document.querySelectorAll("#input-list>div");
		$divs.forEach(fuction(e){
			e.remove();
		});
		if(pageFirst.getMonth()===1){
			pageFirst = new Date(first.getFullYear-1,12,1);
			first= pageFirst;
			if(first.getFullYear%4 ===0){
				pageYear = leapYear;
			}else{
				pageYear = notLeapYear;
			}
		}else{
		pageFirst = new Date(first.getFullYear(),first.getMonth()-1,1);	
		first= pageFirst;
		}
		today = new Date(today.getFullYear(),first.getMonth()-1,today.getDate());
		currentTitle.innerHTML = monthList[first.getMonth()]+'&nbsp;&nbsp;&nbsp;&nbsp;'+first.getFullYear();
		removeCalendar();
		showCalendar();
		showMain();
		clickDate1 = document.getElementById(today.getDate());
		clickDate1.classList.add('active');
		clickStart();
		reshowingList();
	}//end of prev
	
	function next(){
		inputBox.value="";
		const $divs = document.querySelectorAll('#input-list>div');
		$divs.forEach(function(e){
			e.remove();
		});
		const $btns = document.querySelectorAll('#input-list>button');
		$btns.forEach(function(e1){
			e1.remove();
		});
		
		if(pageFirst.getMonth()===12){
			pageFirst = new Date(first.getFullYear()+1,1,1);
			first = pageFirst;
			//윤년 구분
			if(first.getFullYear()%4===0){
				pageYear=leapYear;
			}else{
				pageYear = notLeapYear;				
			}
		}else{
			pageFirst = new Date(first.getFullYear(),first.getMonth()+1,1);
			first = pageFirst;
		}
		
		today = new Date(today.getFullYear(),first.getMonth()+1,today.getDate());
		currentTitle.innerHTML = monthList[first.getMonth()]+'&nbsp;&nbsp;&nbsp;&nbsp;'+first.getFullYear();
		removeCalendar();
		showCalendar();
		showMain();
		clickedDate1 = document.getElementById(today.getDate());
		clickedDate1.classList.add('active');
		clickStart();
		reshowingList();
	}//end of next
	
	
	
</script>
</head>
<body>

	<!-- 스케줄을 실행할 영역-->
	<div class="main">
		<!-- 내용이 넘쳤을때 자동 줄바꿈 -->
		<div class="content-wrap">
			<!-- 내용 왼쪽 정렬 -->
			<div class="content-left">
				<!-- 메인 자동 줄바꿈 -->
				<div class="main-wrap">
					<div id="main-day" class="main-day"></div>
					<div id="main-date" class="main-date"></div>
					<!-- end of main-wrap -->
				</div>
				<!-- 스케줄을 입력하는 곳 -->
				<div class="todo-wrap">
					<!-- 제목 쓰는곳 -->
					<div class="todo-title">Todo List</div>
					<!-- 입력창 자동 줄바꿈 -->
					<div class="input-wrap">
						<!-- 입력창 -->
						<input type="text" placeholder="please write here!!"
							id="input-box" class="input-box">
						<!-- 입력버튼 -->
						<button type="button" id="input-data" class="input-data">INPUT</button>
						<!-- 기존 입력값 출력 -->
						<div id="input-list" class="input-list"></div>
						<!-- end of input-wrap -->
					</div>
					<!-- end of todo-wrap-->
				</div>
				<!-- end of content-left -->
			</div>
			<!-- 달력을 출력할 오른쪽 구역 -->
			<div class="content-right">
				<!-- 달력 테이블 -->
				<table id="calendar" align="center">
					<!-- 테이블 헤더 -->
					<thead>
						<!-- prev , next 버튼 자리 -->
						<tr class="btn-wrap clearfix">
							<!-- prev 이전 달 &#60='<'-->
							<td><label id="prev"> &#60; </label> <!-- end of prev --></td>
							<!-- colspan = 열 합치기 -->
							<td align="center" id="current-year-month" colspan="5"></td>
							<!-- next 다음달 &#62='>'-->
							<td><label id="next">&#62;</label></td>
							<!-- end of btn-wrap clearfix -->
						</tr>
						<tr>
							<td class="sun" align="center">Sun</td>
							<td align="center">Mon</td>
							<td align="center">Tue</td>
							<td align="center">Wed</td>
							<td align="center">Thu</td>
							<td align="center">Fri</td>
							<td class="sat" align="center">Sat</td>
							<!-- end of 요일 -->
						</tr>
						<!-- end of thead -->
					</thead>
					<!-- 테이블 body -->
					<tbody id="calendar-body" class="calendar-body"></tbody>
					<!-- end of calendar -->
				</table>
				<!-- end of content-right -->
			</div>
			<script type="text/javascript">
	showCalendar();
	</script>
			<!-- end of content-wrap -->
		</div>
		<!-- end of main -->
	</div>
</body>
</html>