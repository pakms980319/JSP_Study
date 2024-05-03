<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 등록</title>
<!--  common.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/static/css/common.css" />
<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>
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

	<div class="container">
		<h2 class="mt-5 mb-4">물건 등록 폼</h2>
		<form id="itemForm" method="post">
			<div class="mb-3">
				<label for="itemName" class="form-label">물건 이름</label> <input
					type="text" class="form-control" id="itemName" name="itemName"
					required>
			</div>
			<div class="mb-3">
				<label for="itemType" class="form-label">물건 종류</label> <select
					class="form-select" id="itemType" name="itemType" required>
					<option value="" disabled selected>물건 종류를 선택하세요</option>
					<option value="의류">의류</option>
					<option value="주방">주방</option>
					<option value="스포츠/레저">스포츠/레저</option>
					<option value="가전">가전</option>
					<option value="생활용품">생활용품</option>
					<option value="식품">식품</option>
					<option value="도서">도서</option>
					<option value="뷰피티">뷰피티</option>
					<option value="반려동물 용품">반려동물 용품</option>
					<option value="완구/취미">완구/취미</option>
					<option value="아동">아동</option>
					<option value="홈인테리어">홈인테리어</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="itemPrice" class="form-label">물건 가격</label>
				${NumberFormatExceptionItemPrice} <input type="number"
					class="form-control" id="itemPrice" name="itemPrice" min="0"
					required>
			</div>
			<div class="mb-3">
				<label for="itemCount" class="form-label">물건 개수</label>
				${NumberFormatExceptionItemCount} <input type="number"
					class="form-control" id="itemCount" name="itemCount" min="0"
					required>
			</div>
			<div class="mb-3">

				<label for="itemManufacturingDate" class="form-label">물건
					제조일자</label> ${Manufactur} <input type="date" class="form-control"
					id="itemManufacturingDate" name="itemManufacturingDate" required>
			</div>
			<button type="submit" class="btn btn-primary">등록</button>
			<footer>
				<!-- footer  -->
				<%@ include file="/resources/static/jsp/footer.jsp"%>
			</footer>
		</form>
	</div>
</body>
</html>