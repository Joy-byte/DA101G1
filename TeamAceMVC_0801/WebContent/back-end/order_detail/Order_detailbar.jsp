<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<style>
</style>
</head>
<body>
	<div class="logo" style="margin-right: 30px;">
		<img src="<%=request.getContextPath()%>/back-end/product/icon.jpeg"
			style="width: 100px; height: 100px; margin-left: 70px; margin-top: 30px; margin-bottom: 30px;">

		<ul class="list-group list-group-flush"
			style="margin-left: 30px; width: 200px; text-align: center;">
			<li class="list-group-item"><a style="text-decoration:none; color:black"
				href='<%=request.getContextPath()%>/back-end/order_detail/portal.jsp'>查詢訂單</a></li>
			<li class="list-group-item"><a style="text-decoration:none; color:black"
				href='<%=request.getContextPath()%>/back-end/order_detail/Order_detail.jsp'>全部訂單</a></li>
			<li class="list-group-item"><a style="text-decoration:none; color:black"
				href='<%=request.getContextPath()%>/back-end/order_detail/order_detail_select_pageByCompositeQuery.jsp'>複合查詢</a></li>
			<li class="list-group-item"><a style="text-decoration:none; color:black"
				href='<%=request.getContextPath()%>/back-end/portal/portal.jsp'>回首頁</a></li>
		</ul>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
