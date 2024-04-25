<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>홈페이지</title>

<!-- link  -->
<%@ include file="/resources/static/jsp/link.jsp"%>

<!-- userJoin.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/user/userJoinBusinessMan.css" />

</head>
<body>

<header>
    <!-- nav -->
    <%@ include file="/resources/static/jsp/nav.jsp"%>
</header>

<main>
    <h2>회원가입 양식</h2>

    <form action="/user/join/businessMan" method="post" class="join-form">
        <label for="bussinessManId">사업자 ID:</label>
        <input type="text" id="bussinessManId" name="bussinessManId" required>
        <label for="userId">사용자 ID:</label>
        <input type="text" id="userId" name="userId" required>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>

        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required>
        <label for="phoneNumber">전화번호:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>
        <input type="submit" value="가입">
    </form>
</main>

<footer>
    <!-- footer  -->
    <%@ include file="/resources/static/jsp/footer.jsp"%>
</footer>

</body>
</html>