<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>상품</title>
</head>
<body>
	<script>
		<c:set var="home" value="${pageContext.request.contextPath}"/>
	</script>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>상품 검색 결과</h1>
				<p>
					<a href="${home}">홈으로</a>
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>상품ID : </strong> <span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong>제조사</strong> : ${product.manufacturer}
				</p>
				<p>
					<strong>상품범주</strong> : ${product.category}
				</p>
				<p>
					<strong>재고 수량 </strong> : ${product.unitsInStockStr}
				</p>
				<h4>${product.unitPriceStr}미달러</h4>
				<p>
					<a href="<spring:url value='/market/products' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span>뒤로 가기
					</a> <a href="#" class="btn btn-warning btn-large"> <span
						class="glyphicon-shopping-cart glyphicon"></span>주문하기
					</a>
				</p>
			</div>
		</div>
	</section>


</body>
</html>
