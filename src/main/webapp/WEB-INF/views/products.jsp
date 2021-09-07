<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>상품</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>상품 목록</h1>
			<p>All the available products in our store</p>
		</div>
	</div>
	</section>

	<section class="container">
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<div class="caption">
					<h3>${product.name}</h3>
					<p>${product.description}</p>
					<p>₩${product.unitPriceStr}</p>
					<p>제고 수량 : ${product.unitsInStockStr}</p>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>
