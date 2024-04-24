<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>

<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- login.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/user/login.css" />

</head>
<body>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<!-- 메인 컨텐츠 -->
	<main>
		<h2>로그인</h2>

		<form action="/login" method="post">
			<label for="username">사용자명:</label> <input type="text" id="username"
				name="username" required> <label for="password">비밀번호:</label>
			<input type="password" id="password" name="password" required>

			<input type="submit" value="로그인">
		</form>
	</main>

	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</body>
</html>
