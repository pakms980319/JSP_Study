<%@page import="com.example.app.domain.user.dto.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 상단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/">홈페이지</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<!--  businessMan일 때만 추가 정보 표시 -->
		<%
		Session sessionDto2 = null;
		if (session.getAttribute("session") != null) {
			sessionDto2 = (Session) session.getAttribute("session");
		}
		if (sessionDto2 != null && "BussinessMan".equals(sessionDto2.getRole())) {
		%>
		<ul class="navbar-nav mr-auto">
			<!-- 왼쪽에 추가된 메뉴 -->
			<li class="nav-item"><a class="nav-link"
				href="/item/businessMan/list">등록상품조회</a></li>
			</li>
		</ul>
		<%
		}
		%>


		<ul class="navbar-nav ml-auto">
			<c:choose>
				<c:when test="${sessionScope.session eq null}">
					<!-- session 속성이 존재하지 않는 경우 -->
					<li class="nav-item">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='/user/login'">로그인</button>
					</li>
					<li class="nav-item"><a class="btn btn-outline-light ml-2"
						href="/user/join">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<button type="button" class="btn btn-primary"
							onclick="window.location.href='/user/info'">회원정보 조회</button>
					</li>
					<!-- session 속성이 존재하는 경우 -->
					<li class="nav-item">
						<button type="button" class="btn btn-danger ml-2"
							onclick="window.location.href='/user/logout'">로그아웃</button>
					</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
</nav>