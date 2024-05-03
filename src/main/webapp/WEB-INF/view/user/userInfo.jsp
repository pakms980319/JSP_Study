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

	<main>
		<div class="container">
			<h2>사용자 정보 조회</h2>
			<table class="table">
				<tbody>
					<tr>
						<th scope="row">사용자 ID</th>
						<td>${user.userId}</td>
					</tr>
					<tr>
						<th scope="row">역할</th>
						<td><c:choose>
								<c:when test="${user.role eq 'User'}">
                					일반 사용자
            					</c:when>
								<c:when test="${user.role eq 'BussinessMan'}">
                					사업자
            					</c:when>
								<c:otherwise>
                					기타 역할
            					</c:otherwise>
							</c:choose></td>
					</tr>

					<!--  businessMan일 때만 추가 정보 표시 -->
					<%
					Session sessionDto = null;
					if (session.getAttribute("session") != null) {
						sessionDto = (Session) session.getAttribute("session");
					}
					if (sessionDto != null && "BussinessMan".equals(sessionDto.getRole())) {
					%>
					<tr>
						<th scope="row">사업자 번호</th>
						<td>${bussinessMan.bussinessManId}</td>
					</tr>
					<%
					}
					%>
					<tr>
						<th scope="row">이름</th>
						<td>${user.name}</td>
					</tr>
					<tr>
						<th scope="row">전화번호</th>
						<td>${user.phoneNumber}</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>${user.email}</td>
					</tr>
				</tbody>
			</table>
			<div class="text-right">
				<div class="text-right">
					<a href="/user/update" class="btn btn-primary mr-2">회원수정</a> <a
						href="/user/delete" class="btn btn-danger">회원탈퇴</a>
				</div>

			</div>
		</div>
	</main>
	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</body>
</html>
