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

<link rel="icon" href="data:," />

<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- common.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/common.css" />
</head>
<body>

	<%
		String msg = null;
	
		if (session.getAttribute("msg") != null) {
			msg = (String)session.getAttribute("msg");
		}
		
		session.removeAttribute("msg");
	%>

	<script>
		const msg = "<%=msg%>";
		
		if (msg !== "null")
			alert(msg);
	</script>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<!-- 메인 컨텐츠 -->
	<main>
		<h2>메인 홈페이지</h2>

		<c:choose>
			<c:when test="${sessionScope.session ne null}">
				<!-- session 속성이 존재하지 않는 경우 -->
				<p>${name}님 환영합니다! 이 곳은 메인 홈페이지입니다.</p>
			</c:when>
			<c:otherwise>
				<p>방문자님 환영합니다! 이 곳은 메인 홈페이지입니다.</p>
			</c:otherwise>
		</c:choose>

		<p>원하시는 상품을 검색해 보세요.</p>
		<form class="form-inline" action="/item/list" method="get">
			<input type="hidden" name="type" value="itemName" />
			<input class="form-control mr-sm-2" type="search" placeholder="상품 검색"
				aria-label="Search" name="keyword">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
		</form>
	</main>

	<!-- footer -->
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>

</body>
</html>
