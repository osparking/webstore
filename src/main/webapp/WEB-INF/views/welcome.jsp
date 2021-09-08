<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" 
	content="width=device-width, initial-scale=1">
<title>환영</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div class="jumbotron">
		<h1>${greeting}</h1>
		<p>${tagline}</p>
		<p><a href="customers">고객 목록 보기</a></p>
		<p><a href="update/stock">500미만 상품 제고 1000 증가</a></p>
		<p><a href="products">상품 목록 보기</a></p>
	</div>
</body>
</html>
