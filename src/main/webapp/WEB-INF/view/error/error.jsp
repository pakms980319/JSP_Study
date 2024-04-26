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

<!-- error.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/error/error.css" />

</head>
<body>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<main>
		<!-- 에러 페이지 -->
		<div class="error-page">
			<h3>에러가 발생했습니다</h3>
			<p>${msg}</p>
			<a href="/" class="btn btn-primary">홈으로 돌아가기</a>
		</div>
	</main>

	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</body>
</html>
