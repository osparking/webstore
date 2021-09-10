<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>End Point</title>
</head>
<style>
	p {font-size: 1.5em;}
</style>
<body>
	<script>
		<c:set var="home" value="${pageContext.request.contextPath}"/>
	</script>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>End Point List</h1>
				<p>이 응용이 처리하는 요청 경로(Request Path)</p>
				<p>
					<a href="${home}">홈으로</a>
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<c:forEach items="${reqPaths}" var="reqPath">
			<p>${reqPath}</p>
		</c:forEach>
	</section>
</body>
</html>