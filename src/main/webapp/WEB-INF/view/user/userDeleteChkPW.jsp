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

<!-- userInfoChkPW.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/user/userInfoChkPW.css" />

</head>
<body>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<main>
		<div class="container">
			<form action="/user/delete" method="post">
				<h2 class="text-center">회원 탈퇴</h2>
				<br>
				<div class="form-group">
					<label for="password">비밀번호 확인:</label> <input type="password"
						class="form-control" id="pw" name="pw" required>
					<br>
					<button type="submit" class="btn btn-primary btn-block">확인</button>
				</div>

			</form>
		</div>
	</main>

	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</body>
</html>
