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
		<p><a href="market/product/update?id=P1234">상품(P1234)</a></p>
		<p><a href="customers/add">새 고객 추가</a></p>
		<p><a href="customers">고객 목록 보기</a></p>
		<p><a href="market/update/stock">500미만 상품 제고 1000 증가</a></p>
		<p><a href="market/products/add">신상품 추가</a></p>
		<p><a href="market/products">상품 목록 보기</a></p>
		<p><a href="market/products/Laptop">랩탑 보기</a></p>
		<p><a href="market/products/tablet">태블릿 보기</a></p>
		<p><a href="market/products/filter/params;categories=Laptop,Tablet;
			brands=Google,Dell">상품 2 중 필터</a></p>
		<p><a href="market/product?id=P1234">상품(P1234)</a></p>
		<p><a href="market/products/Tablet/price;low=200000;high=400000?brand=Google">복합조건 검색</a></p>
		<p><a href="market/products/laptop/price;low=500000;high=900000">품종-가격 검색</a></p>
		<p><a href="endpointdoc">종점 목록 보기</a></p>
	</div>
</body>
</html>
