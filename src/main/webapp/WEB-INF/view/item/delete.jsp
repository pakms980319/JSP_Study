<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 삭제</title>
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
		msg = (String) session.getAttribute("msg");
	}

	session.removeAttribute("msg");
	
	String delete = null;

	if (session.getAttribute("delete") != null) {
		delete = (String) session.getAttribute("delete");
	}

	session.removeAttribute("delete");
	%>
	<script>
		const msg = "<%=msg%>";

		if (msg !== "null")
			alert(msg);
		const delete = "<%=delete%>";
		
		if(delete !== "null")
			if(confirm(delete));
		alert("정상적으로 삭제되었습니다.");
		
	</script>

	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<div class="container">
		<h2 class="mt-5 mb-4">상품 삭제 폼</h2>
		<form id="itemForm" method="post">
			<div class="mb-3">
				<label for="itemId" class="form-label">상품 id</label> <input
					type="text" class="form-control" id="itemId" name="itemId"
					required>
			</div>
			<button type="submit" class="btn btn-primary">상품 삭제</button>

		</form>
	</div>
</body>
<footer>
	<!-- footer  -->
	<%@ include file="/resources/static/jsp/footer.jsp"%>
</footer>
</html>