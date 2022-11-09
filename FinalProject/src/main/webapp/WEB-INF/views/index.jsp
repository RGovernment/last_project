<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html lang="ko">
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.104.2">
<title>Memory Gift</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/headers/">
<script type="text/javascript"
	src="https://ssl.gstatic.com/trends_nrtr/3140_RC01/embed_loader.js"></script>
<link href="resources/css/mainPage.css" rel="stylesheet">
<link href="resources/css/pageStyle.css" rel="stylesheet">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main>
		<div class="container">
			<header class="d-flex flex-wrap justify-content-center py-3 mb-4 "
				id="tasic">
				<a href="index"
					class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-decoration-none"
					id="hd-1"> <img id="imsi" src="resources/img/imsi.png"><span
					id="hder-home">Memory Gift</span>
				</a>
				<div class="row">
					<div class="col-md-2 align-self-center" id="index-1">회사소개</div>
					<div class="col-md-2 align-self-center" id="index-1" onclick="location.href='res'">대관</div>
					<div class="col-md-2 align-self-center" id="index-1" onclick="location.href='card'">리뷰</div>
					<div class="col-md-2"></div>
					<div class="col-md-2 align-self-center" id="index-1">옵션</div>
					<div class="col-md-2 align-self-center" id="index-1">로그인</div>
				</div>
				<div class="row">
					<div class="col-2 align-self-center" id="index-2">소개</div>
					<div class="col-2 align-self-center" id="index-2" onclick="location.href='res'">대관</div>
					<div class="col-2 align-self-center" id="index-2" onclick="location.href='card'">리뷰</div>
					<div class="col-2"></div>
					<div class="col-2 align-self-center" id="index-2">옵션</div>
					<div class="col-2 align-self-center" id="index-2">로그인</div>
				</div>
			</header>
		</div>
		<div class="container mb-0" >
			<div class="main-section">
				<input type="radio" name="main-slide" id="main-slide01" checked
					hidden="hidden"> <input type="radio" name="main-slide"
					id="main-slide02" hidden="hidden"> <input type="radio"
					name="main-slide" id="main-slide03" hidden="hidden">
				<div class="main-slidewrap">
					<ul class="main-slidelist" style="padding-left: 0; margin: 0;">
						<li><a>
								<div class="text-box">
									<p>파티를 원하시나요?</p>
									<p>여러분과 친구들의 기억에 작은 선물을 남겨보세요.</p>
								</div> <label for="main-slide03" class="left"></label> <img
								class=img-fluid src="resources/img/main_party1.jpg"> <label
								for="main-slide02" class="right"></label>
						</a></li>
						<li><a>
								<div class="text-box2">
									<p>다양한 것이 준비되어있습니다.</p>
									<p>파티에 필요한 음식과 사진, 잊지못할 추억을 남길 여러 재밌는 도구들까지 말이죠.</p>
								</div> <label for="main-slide01" class="left"></label> <img
								class=img-fluid src="resources/img/main_party2.jpg"> <label
								for="main-slide03" class="right"></label>
						</a></li>
						<li><a>
								<div class="text-box3">
									<p>기다리고 있습니다!</p>
									<p>우리 Memory Gift는 언제나 여러분의 방문을 기다리고 있습니다.</p>
								</div> <label for="main-slide02" class="left"></label> <img
								class=img-fluid src="resources/img/main_party3.jpg"> <label
								for="main-slide01" class="right"></label>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<br>
		<br>
		<div class="container">
			<div class="row" id="txt">
				<div class="col-md-12 align-self-center"><p id="title-text2">Single Service
				단품 서비스<p>
				<p id="mobile-text1">(PC에서 자세히 확인)<p></div>
			<br>	
			</div>
			<div class="row" id="table-hd">
				<div class="col-md-4 align-self-center">서비스</div>
				<div class="col-md-4 align-self-center">단가</div>
				<div class="col-md-4 align-self-center">내용</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">포토스냅</div>
				<div class="col-md-4 align-self-center">10만원/12만원</div>
				<div class="col-md-4 align-self-center">스냅 A / 스냅 B</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">파티룸 데코레이션</div>
				<div class="col-md-4 align-self-center">풍선 및 룸 데코레이션 : 5만원
				플랜카드 제작 : 3만원
				세트 : 7만원</div>
				<div class="col-md-4 align-self-center" >풍선 및 데코레이션 스타일 A/B/C중 택1
				플랜카드 제작시 문구 및 사진인쇄 가능</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">케이크</div>
				<div class="col-md-4 align-self-center">레터링 케이크 : 5만원
				얼굴 인화 케이크 : 7만원</div>
				<div class="col-md-4 align-self-center">최고의 품질로 만든 수제 케이크
				초코/생크림 택1</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">주류,얼음 및 아이스박스</div>
				<div class="col-md-4 align-self-center">콜라/사이다/환타 : 1200원
				물 : 1000원
				소주 : 1600원 / 맥주 : 2000원 / 양주 : 문의</div>
				<div class="col-md-4 align-self-center">파티 시작시 바로 시원하게 즐기실 수 있도록 미리 시원한 주류들을 냉장 보관 및 아이스박스 보관해드립니다!</div>
			</div>
			<div class="row" id="table2-hd">
				<div class="col-4 align-self-center">서비스</div>
				<div class="col-4 align-self-center">단가</div>
				<div class="col-4 align-self-center">내용</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">포토스냅</div>
				<div class="col-4 align-self-center">10만원/12만원</div>
				<div class="col-4 align-self-center">스냅 A / 스냅 B</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">파티룸 데코레이션</div>
				<div class="col-4 align-self-center">풍선 및 룸 데코레이션
				플랜카드 제작
				세트</div>
				<div class="col-4 align-self-center" >풍선 데코 스타일 A/B/C 택1
				플랜카드 제작시 문구 +사진인쇄 O</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">케이크</div>
				<div class="col-4 align-self-center">레터링 케이크
				얼굴 인화 케이크</div>
				<div class="col-4 align-self-center">최고 품질 수제 케이크
				초코/생크림 택1</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">주류,얼음 및 아이스박스</div>
				<div class="col-4 align-self-center">콜라/사이다/환타 and 물 and 주류
				양주는 문의</div>
				<div class="col-4 align-self-center">예약시 냉장 보관 및 아이스박스 보관 서비스</div>
			</div>		
		</div>
			<br>
			<br>
			<br>
			<div class="container">
			<div class="row" id="txt">
				<div class="col-md-12 align-self-center"><p id="title-text2">Package Service
				패키지 서비스<p></div>
			<br>	
			</div>
			<div class="row" id="table-hd">
				<div class="col-md-4 align-self-center">서비스</div>
				<div class="col-md-4 align-self-center">단가</div>
				<div class="col-md-4 align-self-center">내용</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">올인원 패키지(Hot!)</div>
				<div class="col-md-4 align-self-center">23만원</div>
				<div class="col-md-4 align-self-center">포토스냅B + 파티룸 데코레이션 세트 + 얼굴인화 케이크</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">내맘쏙 패키지 A</div>
				<div class="col-md-4 align-self-center">18만원</div>
				<div class="col-md-4 align-self-center" >포토스냅B + 파티룸 데코레이션 세트</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">내맘쏙 패키지 B</div>
				<div class="col-md-4 align-self-center">16만원</div>
				<div class="col-md-4 align-self-center">포토스냅A + 얼굴인화 케이크</div>
			</div>
			<div class="row" id="table-con">
				<div class="col-md-4 align-self-center">내맘쏙 패키지 C</div>
				<div class="col-md-4 align-self-center">11만원</div>
				<div class="col-md-4 align-self-center">파티룸 데코레이션 세트 + 레터링 케이크</div>
			</div>
			<div class="row" id="table2-hd">
				<div class="col-4 align-self-center">서비스</div>
				<div class="col-4 align-self-center">단가</div>
				<div class="col-4 align-self-center">내용</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">올인원 패키지(Hot!)</div>
				<div class="col-4 align-self-center">23만원</div>
				<div class="col-4 align-self-center">포토스냅B + 파티룸 데코레이션 세트 + 얼굴인화 케이크</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">내맘쏙 패키지 A</div>
				<div class="col-4 align-self-center">18만원</div>
				<div class="col-4 align-self-center" >포토스냅B + 파티룸 데코레이션 세트</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">내맘쏙 패키지 B</div>
				<div class="col-4 align-self-center">16만원</div>
				<div class="col-4 align-self-center">포토스냅A + 얼굴인화 케이크</div>
			</div>
			<div class="row" id="table2-con">
				<div class="col-4 align-self-center">내맘쏙 패키지 C</div>
				<div class="col-4 align-self-center">11만원</div>
				<div class="col-4 align-self-center">파티룸 데코레이션 세트 + 레터링 케이크</div>
			</div>		
		</div>
		
		<div class="container-fluid">
			<footer class="py-3 my-4" style="font-size: 100%;">
				<ul class="nav justify-content-center border-bottom pb-3 mb-3">
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">사이트 소개</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">이용 약관</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">고객지원</a></li>
				</ul>
				<p class="text-center text-muted">&copy; 2022 Revrin</p>
			</footer>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
	</main>
</body>
</html>
