<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 상세</title>
<!--  common.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/common.css" />
<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- userInfo.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/user/userInfo.css" />
</head>
<body>
	<%
	String msg = null;

	if (session.getAttribute("msg") != null) {
		msg = (String) session.getAttribute("msg");
	}

	session.removeAttribute("msg");
	%>
	<script>
		const msg = "<%=msg%>
		";

		if (msg !== "null")
			alert(msg);
	</script>
	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>
	<main>
		<div class="container">
			<h1>아이템 상세 정보</h1>

			<c:if test="${not empty item}">
				<table class="table">
					<tr>
						<th scope="row">아이템 ID</th>
						<td>${item.itemId}</td>
					</tr>
					<tr>
						<th scope="row">판매자 이름</th>
						<td>${userName}</td>
					</tr>
					<tr>
						<th scope="row">아이템명</th>
						<td>${item.itemName}</td>
					</tr>
					<tr>
						<th scope="row">아이템 종류</th>
						<td>${item.itemType}</td>
					</tr>
					<tr>
						<th scope="row">아이템 가격</th>
						<td>${item.itemPrice}</td>
					</tr>
					<tr>
						<th scope="row">아이템 수량</th>
						<td>${item.itemCount}</td>
					</tr>
					<tr>
						<th scope="row">제조일자</th>
						<td>${item.itemManufacturingDate}</td>
					</tr>
				</table>
				<div class="text-right">
					<a href="/user/update" class="btn btn-primary mr-2">회원수정</a> <a
						href="/user/delete" class="btn btn-danger">회원탈퇴</a>
				</div>
			</c:if>
			<c:if test="${empty item}">
				<p>아이템이 존재하지 않습니다.</p>
			</c:if>
		</div>
	</main>
	<footer>
		<!-- footer  -->
		<%@ include file="/resources/static/jsp/footer.jsp"%>
	</footer>
</html>