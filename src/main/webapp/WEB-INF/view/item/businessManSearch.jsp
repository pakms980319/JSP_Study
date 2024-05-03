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
	<header>
		<!-- nav -->
		<%@ include file="/resources/static/jsp/nav.jsp"%>
	</header>

	<!-- 메인 컨텐츠 -->
	<main class="layout-150 mb-5">

		<section class=binfocrum-block>
			<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/">HOME</a></li>
					<li class="breadcrumb-item active" aria-current="page">Item(LIST)</li>
				</ol>
			</nav>

		</section>

		<section class="search-block layout-150">

			<form action="${pageContext.request.contextPath}/item/businessMan/list">
				<div class="m-2">
					<select name="type" class="form-select">
						<option value="itemName" selected>상품명</option>
						<option value="itemType">카테고리</option>
					</select>
				</div>
				<div class="m-2">
					<input name="keyword" placeholder="KEYWORD" class="form-control">
				</div>
				<%-- <input type="hidden" name="pageNo"
					value="${pageDto.criteria.pageno}" /> --%>

				<div class="m-2">
					<button class="btn btn-secondary">조회</button>
				</div>

			</form>
		</section>

		<div class="main-block">
			<aside class=left></aside>

			<div class=right>
				<section class="show-block">
					<%-- ${pageDto} --%>
					<div>

						<div>
							TOTAL PAGE: <span> ${pageDto.totalpage}</span>
						</div>

						<div>
							NOW PAGE: <span> ${pageDto.criteria.pageno}</span>
						</div>
					</div>
				</section>

				<section>
					<table class="table">
						<thead class="table-secondary">
							<tr>
								<th scope="col">상품Id</th>
								<th scope="col">상품명</th>
								<th scope="col">상품 카테고리</th>
								<th scope="col">상품가격</th>
								<th scope="col">재고</th>
							</tr>
						</thead>
						<c:forEach var="itemDto" items="${list}" varStatus="status">
							<tr>

								<td><c:out value="${itemDto.itemId}" /></td>
								<td><a
									href="${pageContext.request.contextPath}/item/businessMan/info?itemId=${itemDto.itemId}">
										<c:out value="${itemDto.itemName}" />
								</a></td>
								<td><c:out value="${itemDto.itemType}" /></td>
								<td><c:out value="${itemDto.itemPrice}" /></td>
								<td><c:out value="${itemDto.itemCount}" /></td>
							</tr>
						</c:forEach>
					</table>
				</section>

				<!-- paging -->
				<section>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<!-- prev -->

							<c:if test="${pageDto.prev}">
								<li class="page-item">
									<!-- keyword 존재유무에 따라 선별하기 --> <a class="page-link"
									href="${pageContext.request.contextPath}/item/businessMan/list?pageNo=${pageDto.nowBlock * pageDto.pagePerBlock - pageDto.pagePerBlock*2 + 1}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a>


								</li>
							</c:if>


							<!-- paging -->
							<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage }"
								var="pageno" step="1">

								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/item/businessMan/list?pageNo=${pageno}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}">${pageno}</a></li>
							</c:forEach>

							<!-- next -->
							<c:if test="${pageDto.next}">
								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/item/businessMan/list?pageNo=${pageDto.nowBlock*pageDto.pagePerBlock+1}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:if>

						</ul>
					</nav>
				</section>

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
