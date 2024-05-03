<%@page import="com.example.app.domain.user.dto.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>홈페이지</title>


<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- userInfo.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/item/itemInfo.css" />
</head>
<body>	
	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>
	
	<main>
		<div class="container">
		<h2>상품 정보 조회</h2>
		<table class="table">
			<tbody>
				<tr>
					<th scope="row">상품 번호</th>
					<td>${item.itemId}</td>
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
					<th scope="row">상품 이름</th>
					<td>${item.itemName}</td>
				</tr>
				<tr>
					<th scope="row">상품 타입</th>
					<td>${item.itemType}</td>
				</tr>
				<tr>
					<th scope="row">상품 가격</th>
					<td>${item.itemPrice}</td>
				</tr>
				<tr>
					<th scope="row">상품 재고</th>
					<td>${item.itemCount}</td>
				</tr>
				<tr>
					<th scope="row">상품 등록일</th>
					<td>${item.itemManufacturingDate}</td>
				</tr>
			</tbody>
		</table>
			<div class="text-right">
				<a href="/item/update" class="btn btn-primary mr-2">상품 수정</a> <a
					href="/item/delete" class="btn btn-danger">상품 삭제</a>
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