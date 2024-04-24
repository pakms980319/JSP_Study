<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 상단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/">홈페이지</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<button type="button" class="btn btn-primary" onclick="window.location.href='/user/login'">로그인</button>
			</li>
			<li class="nav-item"><a class="btn btn-outline-light ml-2"
				href="/user/join">회원가입</a></li>
		</ul>
	</div>
</nav>