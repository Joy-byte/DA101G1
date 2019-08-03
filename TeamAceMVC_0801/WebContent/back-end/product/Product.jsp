<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<!DOCTYPE html>
<html>
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

.content {
	display: flex;
	background-size: cover;
	margin-bottom: 50px;
	width: 100%;
	display: flex;
	margin-bottom: 50px;
}
</style>

</head>


<body bgcolor='white'>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

     <div class="main" style="display:flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/product/Productbar.jsp" flush="true" />
		</div>
		<div style="width:100%;">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div style="background-color:#d6d6ad; height:50px; width:100%; line-height:50px; ">
				<h5 style="line-height:50px; margin-left:10px">所有商品</h5>
			</div>
			<div class="content container">
				<jsp:include page="listAllProduct.jsp" flush="true" />
				<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
				<%-- </c:import> --%>
				<%-- //動態不行用<%=request.getContextPath()%> --%>

			</div>
		</div>
	</div>
</body>
</html>