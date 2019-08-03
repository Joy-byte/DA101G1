<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

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

<title>OrderComplete!</title>

<style>
body {
	font-family: Microsoft JhengHei;
}

.container {
	display: flex;
	position: absolute;
	top: 20%;
	left: 0;
	right: 0;
	bottom: 30%;
	
}
.adjust{
	display: flex;
	flex-direction: column;
    justify-content: space-between;
}
</style>
</head>
<body>
	<div class="container justify-content-center">
		<div class="card" style="width: 35rem;">
			<div class="card-body adjust">
				<div>
					<h5 class="card-title">Good&nbspJob!&nbsp儲值已成立!</h5>
				</div>
				<div>
<%-- 					<h6 class="card-subtitle mb-2 text-muted">&middot;&nbsp訂單編號&nbsp<%=ordernumber%></h6> --%>
					<h6 class="card-subtitle mb-2 text-muted">&middot;&nbsp會員點數:&nbsp<%=request.getAttribute("new_mem_amo")%>YO</h6>
					<h6 class="card-subtitle mb-2 text-muted">&middot;&nbsp這次儲值金額:&nbsp<%=request.getAttribute("deposit_amo")%>YO</h6>
					<p class="card-text">&middot;&nbsp點擊商城首頁會員圖像可查訪儲值詳情</p>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/front-end/product/Shop.jsp" class="card-link"><br>
						<button type="button" class="btn btn-dark" Style="width: 100%;">返回商城首頁</button></a>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
