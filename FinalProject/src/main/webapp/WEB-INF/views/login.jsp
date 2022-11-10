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
</style>
<link href="resources/css/loginPage.css" rel="stylesheet">
<link href="resources/css/pageStyle.css" rel="stylesheet">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('8890a67c089173194979845f9389950d');
console.log(Kakao.isInitialized());
</script>
<script>
        function kakaoLogin() {
            window.Kakao.Auth.login({
                scope: 'profile, gender, age_range', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                    console.log(response) // 로그인 성공하면 받아오는 데이터
                    window.Kakao.API.request({ // 사용자 정보 가져오기 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account)
                        }
                    });
                    // window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
</script>

<body class="text-center">
	<main class="form-signin w-100 m-auto">
		<form action="login">
			<img class="mb-4" src="resources/img/imsi.png" alt="" width="72"
				height="57">
			<h1 class="h3 mb-3 fw-normal" id="login-text2">로그인</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput"
					placeholder="name@example.com"> <label for="floatingInput"
					id="login-text">이메일을 입력해주세요.</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					placeholder="Password"> <label for="floatingPassword"
					id="login-text">비밀번호를 입력해주세요.</label>
			</div>

			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me"
					id="login-text"> 아이디 기억
				</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit"
				id="login-text">로그인</button>
			<button class="btn btn-primary mr-2 mt-2 mb-1" id="login-text"
				onclick="location.href='sign'">회원가입</button>
			<hr>
			<div class="txt1 text-center p-t-54 p-b-20">
				<span id="login-text"> 혹은 SNS 로그인 </span>
			</div>
			<div>
				<a href="javascript:kakaoLogin();"><img src="./kakao_login.png"
					alt="카카오계정 로그인" style="height: 100px;" /></a>


			</div>
		</form>
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

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
	</main>
</body>
</html>