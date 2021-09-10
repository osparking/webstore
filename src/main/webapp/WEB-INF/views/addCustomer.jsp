<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>고객 등록</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>고객님 정보</h1>
				<p>새 고객 입력</p>
			</div>
		</div>
	</section>
	<section>
		<form:form method="POST" modelAttribute="newCustomer"
			class="form-horizontal">
			<fieldset>
				<legend>새 고객 정보 입력</legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" 
						for="customerId">
						고객 ID </label>
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" 
						type="text"
							class="form:input-large" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						고객 성명</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="address">
						주소</label>
					<div class="col-lg-10">
						<form:input id="address" path="address" type="text"
							class="form:input-large" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" 
					for="noOfOrdersMade">
						주문 건수</label>
					<div class="col-lg-10">
						<form:input id="noOfOrdersMade" path="noOfOrdersMade" 
						type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" 
						class="btn btn-primary"
							value="저장" />
					</div>
				</div>
			</fieldset>
		</form:form>
</body>
</html>