<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<%-- <jsp:useBean id="listOrder_detailByCompositeQuery" scope="request" type="java.util.List<Order_detailVO>" /> <!-- 於EL此行可省略 --> --%>




<html>
<head>
<title>複合查詢 - listEmps_ByCompositeQuery.jsp</title>

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
	margin: 0;
	padding: 0;
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

<style>
table {
	width: auto;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>


</head>

<body bgcolor='white'>
	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/order_detail/Order_detailbar.jsp"
				flush="true" />
		</div>

		<div style="width:100%">
		   <jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div
				style="background-color: #cf9e9e; height: 50px; width: 100%; line-height: 50px">
				<h5 style="line-height: 50px; margin-left: 10px">查詢訂單</h5>
			</div>

			<div class="content container">
				<table style="margin-top:50px">
					<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
						<th>訂單編號</th>
						<th>會員編號</th>
						<!-- 		<th>廠商編號</th> -->
						<th>訂單狀態</th>
						<th>訂單總金額</th>
						<th>訂單時間</th>
						<th>取貨地址</th>
						<th>收件人姓名</th>
						<th>收件人電話</th>
						<th></th>
						<th></th>
					</tr>
					<%-- 	<%@ include file="/pages/page1_ByCompositeQuery.file" %> --%>
					<%-- 	<c:forEach var="empVO" items="${listEmps_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
					<c:forEach var="order_detailVO"
						items="${listOrder_detailByCompositeQuery}">
						<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;" align='center' valign='middle'
							${(order_detailVO.order_no==param.order_no) ? 'bgcolor=#CCCCFF':''}>
							<!--將修改的那一筆加入對比色而已-->
							<%-- 			<td>${order_detailVO.order_no}</td> --%>
							<td><A
								href="<%=request.getContextPath()%>/Order_listServlet1?order_no=${order_detailVO.order_no}&action=getOne_From06">${order_detailVO.order_no}</a></td>
							<td>${order_detailVO.mem_no}</td>
							<%-- 			<td>${order_detailVO.merchant_no}</td> --%>
							<td><c:set var="order_status"
									value="${order_detailVO.order_status}" /> <c:if
									test="${order_status == 'O1'}">
									<%="撿貨"%>
								</c:if> <c:if test="${order_status == 'O2'}">
									<%="已出貨"%>
								</c:if> <c:if test="${order_status == 'O3'}">
									<%="取消訂單"%>
								</c:if> <c:if test="${order_status == 'O4'}">
									<%="退貨"%>
								</c:if></td>
							<td>${order_detailVO.order_amosum}</td>
							<td><fmt:formatDate value="${order_detailVO.order_time}"
									pattern="yyyy-MM-dd" /></td>
							<td>${order_detailVO.order_cusadr}</td>
							<td>${order_detailVO.order_cusname}</td>
							<td>${order_detailVO.order_cusphone}</td>
						</tr>
					</c:forEach>
				</table>
				<%-- <%@ include file="pages/page2_ByCompositeQuery.file" %> --%>
			</div>
		</div>
	</div>

	<!-- 	<br>本網頁的路徑: -->
	<!-- 	<br> -->
	<%-- 	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
	<%-- 		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> --%>
	<!-- 	</b> -->


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