<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>홈페이지</title>

<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- join.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/user/join.css" />

</head>
<body>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<!-- 메인 컨텐츠 -->
	<main style="text-align: center; padding-top: 30vh;">
		<h2>회원 가입</h2>
		<!-- 유저 및 사업자 회원가입 버튼 -->
		<div class="mt-5">
			<a href="/user/join/user" class="btn btn-primary btn-lg mr-4">일반 유저
				회원가입</a> <a href="/business/signup" class="btn btn-primary btn-lg">사업자
				회원가입</a>
		</div>
	</main>

	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</body>
</html>
