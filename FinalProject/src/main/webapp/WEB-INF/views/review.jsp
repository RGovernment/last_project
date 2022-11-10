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
<style type="text/css">
</style>
<link href="resources/css/pageStyle.css" rel="stylesheet">
<link href="resources/css/card.css" rel="stylesheet">
</head>
<body>

	<div class="container-fluid mb-0" id="nav-bg">
		<header class="d-flex flex-wrap justify-content-center py-0 mb-0">
			<div class="row">
				<div class="col-md-4 align-self-center" id="index-1-title">
					<a href="index"
						class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-decoration-none"
						id="hd-1"> <img id="imsi" src="resources/img/imsi.png"><span
						id="title-span">Memory Gift</span></a>
				</div>
				<div class="col-md-2"></div>
				<div class="col-md-1" id="index-1" onclick="">소개</div>
				<div class="col-md-1" id="index-1" onclick="location.href='card'">대관</div>
				<div class="col-md-1" id="index-1" onclick="location.href='res'">후기</div>
				<div class="col-md-1" id="index-1" onclick="">가격</div>
				<div class="col-md-1" id="index-1">옵션</div>
				<div class="col-md-1" id="index-1">접속</div>
			</div>
			<div class="row">
				<div class="col-2 align-self-center" id="index-2-title">
					<a href="index"
						class="d-flex align-items-center text-decoration-none" id="hd-1"><img
						id="imsi" src="resources/img/imsi.png"></a>
				</div>
				<div class="col-2 " id="index-2">소개</div>
				<div class="col-2" id="index-2" onclick="location.href='card'">대관</div>
				<div class="col-2" id="index-2" onclick="location.href='res'">후기</div>
				<div class="col-2 align-self-center" id="index-2">옵션</div>
				<div class="col-2 align-self-center" id="index-2">접속</div>
			</div>
		</header>
	</div>

	<main role="main">
		<div class="container">
			<form class="row">
				<div class="col-md-4">
					<label for="inputaddress" class="col-form-label"
						style="font-size: 30px;">지역</label> <select id="inputaddress"
						class="for-select">
						<option selected>부평구</option>
						<option>미추홀구</option>
					</select>
				</div>
				<div class="col-md-4">
					<label for="inputdate" class="col-form-label"
						style="font-size: 30px;">날짜</label> <input type="date"
						id="inputdate" class="form-control" value="2022-11-09">
				</div>
				<div class="col-md-4">
					<label for="inputpeople" class="col-form-label"
						style="font-size: 30px;">인원</label> <select id="inputpeople"
						class="for-select">
						<option selected>1</option>
						<option>2</option>
					</select>
				</div>
				<div class="col-md-12">
					<label for="inputbudget" class="col-form-label"
						style="font-size: 30px;">예산</label> <input type="range"
						id="inputbudget" class="for-range" min="5" max="100" value="5"
						oninput="document.getElementById('budget').innerHTML=this.value;"
						style="width: 100%;">
					<p>
						<span id="budget"></span>만 원
					</p>
				</div>
			</form>
		</div>

		<div class="album py-5">
			<div class="container">

				<div class="row">
					<%
					for (int i = 0; i < 10; i++) {
					%>


					<div class="col-md-3">
						<div class="card mb-4 border-0">
							<img class="card-img-top" src="resources/img/picture.jpg"
								alt="Card image cap">
							<div class="card-body p-0">
								<div id="desc">쓰고싶은 말</div>
								<div id="title">타이틀</div>
								<div id="address">주소</div>
								<div id="price">
									가격
									<mark id="time">시간</mark>
								</div>
								<div>★</div>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>

	</main>

	<div class="container-fluid  mt-3" id="ft-bg">
		<footer class="pb-3 mb-0">
			<ul class="nav justify-content-center pb-2 mb-0">
				<li class="nav-item"><a href="terms"
					class="nav-link px-2 text-muted">이용 약관</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">고객지원</a></li>
			</ul>
			<p class="text-center text-muted">&copy; 2022 Memory gift</p>
		</footer>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
</body>
</html>