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
<title>레브린</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/headers/">
<script type="text/javascript"
	src="https://ssl.gstatic.com/trends_nrtr/3140_RC01/embed_loader.js"></script>

<link href="resources/css/pageStyle.css" rel="stylesheet">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main>
		<div class="container-fluid">
			<header class="d-flex flex-wrap justify-content-center py-3 mb-4 "
				id="tasic">
				<a href="index"
					class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-decoration-none"
					id="hd-1"> <img id="imsi" src="resources/img/imsi.png"><span
					id="hder-home">홈페이지 이름</span>
				</a>
				<div class="row">
					<div class="col-md-3" onclick="location.href='item'">아이템</div>
					<div class="col-md-3">랭킹</div>
					<div class="col-md-3">공지사항</div>
					<div class="col-md-3">토론게시판</div>
				</div>
				<div class="row">
					<div class="col-3" onclick="location.href='item'">아이템</div>
					<div class="col-3">랭킹</div>
					<div class="col-3">공지사항</div>
					<div class="col-3">토론게시판</div>
				</div>
			</header>
		</div>
		<div class="container-fluid"
			style="font-size: 300%; text-align: center; padding-bottom: 5px;">
			<div id="kimtasic" class="bg-image">
				<!-- <div style="font-size: 100px; text-align: center;">제목</div> -->
				<div id="widget">
					<script type="text/javascript"
						src="https://ssl.gstatic.com/trends_nrtr/3140_RC01/embed_loader.js"></script>
					<script type="text/javascript">
						trends.embed
								.renderWidget(
										"dailytrends",
										"",
										{
											"geo" : "KR",
											"guestPath" : "https://trends.google.com:443/trends/embed/"
										});
					</script>
				</div>
				<div id="title">제목제목</div>
				<div style="clear: both; display: none;"></div>

			</div>
		</div>

		<div class="container">
			<div class="form-group input-group row g-0">
				<div class="col-md-5 input-group-addon mb-3">
					<input type="text" class="form-control" placeholder="검색어를 입력해주세요."
						aria-label="검색어를 입력해주세요." aria-describedby="basic-addon2"
						id="textinput">
				</div>
				<span class="col-md-2 input-froup-addon" id="vs">VS</span>
				<div class="col-md-5 input-group-addon mb-3">
					<input type="text" class="form-control" placeholder="검색어를 입력해주세요."
						aria-label="검색어를 입력해주세요." aria-describedby="basic-addon2"
						id="textinput">
				</div>
			</div>
			<div style="text-align: center;">
				<button class="btn btn-outline-primary" type="submit"
					id="sch-button">검색</button>
			</div>
		</div>
		<div id="muner-button">
			<img src=resources/img/muner.png style="height: 50px; width: 50px;"
				onclick="location.href='login'"></img>
		</div>
		<div class="container-fluid">
			<footer class="py-3 my-4" style="font-size: 160%;">
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