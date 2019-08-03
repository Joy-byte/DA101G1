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
	width: 100%;
	background-size: cover;
	margin-bottom: 50px;
}

.status {
	flex-direction: colum;
	text-align: center;
	margin-top: 30px;
	margin-right: 15px;
	margin-left: 15px;
	margin-bottom: 30px;
	background-color: #fff;
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
	<div class="main" style="display: flex; font-family: Microsoft JhengHei;">
		<div class="logo" style="margin-right: 30px;">
			<img src="<%=request.getContextPath()%>/back-end/promotion/icon.jpeg"
			style="width: 100px; height: 100px; margin-left: 70px; margin-top: 30px; margin-bottom: 30px;">

		<ul class="list-group list-group-flush "
			style="margin-left: 30px; width: 200px; text-align: center;">
			<li class="list-group-item"><a style="text-decoration:none; color:black"
				href='<%=request.getContextPath()%>/back-end/portal/portal.jsp'>回首頁</a></li>
		</ul>
		</div>
		<div style="width:100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div style="background-color:#ffebcd; height:50px; width:100%; line-height:50px">
				<h5 style="line-height:50px; margin-left:20px;">所有儲值</h5>
			</div>
			<div class="content">
				<jsp:include page="listAllDeposit.jsp" flush="true" />
				<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
				<%-- </c:import> --%>
				<%-- //動態不行用<%=request.getContextPath()%> --%>

			</div>
		</div>
	</div>
</body>
</html>