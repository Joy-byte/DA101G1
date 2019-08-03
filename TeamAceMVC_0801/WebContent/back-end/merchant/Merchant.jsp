<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchant.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MerchantService merchantSvc = new MerchantService();
	List<MerchantVO> list = merchantSvc.getAll();
	pageContext.setAttribute("list", list);
%>

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
body{
font-family: Microsoft JhengHei;
}
.content {
	display: flex;
	width: auto;
	margin: 0;
	padding: 0;	
	margin-bottom: 50px;
}

.main {
	display: flex;
}
</style>

<style>
table {
	width: auto;
	margin-top: 5px;
	margin-bottom: 5px;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>

<body style="font-family: Microsoft JhengHei;"> 

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
		<div>
			<jsp:include page="/back-end/merchant/Merchantbar.jsp" flush="true" />
		</div>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div>
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div style="background-color:#d6d6ad; height:50px; width:auto;">
				<h5 style="margin-left:20px; line-height:50px;">所有廠商</h5>
			</div>
			<div class="content">
				<jsp:include page="listAllMerchant.jsp" flush="true" />
				<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
				<%-- </c:import> --%>
				<%-- //動態不行用<%=request.getContextPath()%> --%>
			</div>
		</div>
	</div>
</body>
</html>