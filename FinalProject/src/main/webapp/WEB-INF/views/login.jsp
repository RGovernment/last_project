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
<title>로그인 페이지</title>
<style type="text/css">
html, body {
	height: 100%;
}

body {
	display: flex;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
}

#login-text{
	font-size: 140%;
}
#login-text2{
	font-size: 250%;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
<link href="resources/css/pageStyle.css" rel="stylesheet">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin w-100 m-auto">
		<form>
			<img class="mb-4" src="resources/img/imsi.png" alt="" width="72"
				height="57">
			<h1 class="h3 mb-3 fw-normal" id="login-text2">로그인</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput"
					placeholder="name@example.com"> <label for="floatingInput" id="login-text">이메일을
					입력해주세요.</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					placeholder="Password"> <label for="floatingPassword" id="login-text">비밀번호를
					입력해주세요.</label>
			</div>

			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me" id="login-text">
					아이디 기억
				</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit" id="login-text">로그인</button>
			<button class="btn btn-primary mr-2 mt-2 mb-1" id="login-text">회원가입</button>
			<hr>
			<div class="txt1 text-center p-t-54 p-b-20">
				<span id="login-text"> 혹은 SNS 로그인 </span>
			</div>
			<div>
				<button class="social-login"></button>
				<button class="social-login"></button>
				<button class="social-login"></button>
			
			</div>
		</form>
		<div class="container-fluid">
			<footer class="py-3 my-4" style="font-size: 160%;">
				<ul class="nav justify-content-center border-bottom pb-3 mb-3">
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">이용 약관</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
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