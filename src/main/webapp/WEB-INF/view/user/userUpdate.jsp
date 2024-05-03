<%@page import="com.example.app.domain.user.dto.Session"%>
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

<!-- userInfo.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/user/userInfo.css" />

</head>
<body>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<main
		style="display: flex; justify-content: center; align-items: center; height: 100vh;">
		<div class="container" style="width: 50%;">
			<h2>사용자 정보 수정</h2> <br />
			<form action="/user/update" method="post">
				<div class="form-group">
					<label for="name">비밀번호 확인:</label> <input type="password"
						class="form-control" id="password" name="password" value=""
						style="width: 100%;" required>
				</div>
				<br />
				
				<div class="form-group">
					<label for="name">수정할 비밀번호:</label> <input type="password"
						class="form-control" id="repassword" name="repassword" value=""
						style="width: 100%;" required>
				</div>
				<div class="form-group">
					<label for="name">이름:</label> <input type="text"
						class="form-control" id="name" name="name" value="${user.name}"
						style="width: 100%;" required>
				</div>
				<div class="form-group">
					<label for="phoneNumber">전화번호:</label> <input type="text"
						class="form-control" id="phoneNumber" name="phoneNumber"
						value="${user.phoneNumber}" style="width: 100%;" required>
				</div>
				<div class="form-group">
					<label for="email">이메일:</label> <input type="email"
						class="form-control" id="email" name="email" value="${user.email}"
						style="width: 100%;" required>
				</div>
				<button type="submit" class="btn btn-primary btn-block">수정</button>
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
