<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	List<Order_listVO> list = (List<Order_listVO>) request.getAttribute("List<Order_listVO>"); //EmpServlet.java(Concroller), 存入req的merchantVO物件
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
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

<style>
.content {
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
	width: 1000px;
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

<body>

	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/order_detail/Order_detailbar.jsp"
				flush="true" />
		</div>

		<div style="display: flex; flex-direction:column; width:100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div
				style="background-color: #d6d6ad; height: 50px; width: 100%; line-height: 50px;">
				<h5 style="line-height: 50px; margin-left: 20px;">查詢訂單</h5>
			</div>

			<div class="content container">
				<table style="margin-top:50px">
					<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
						<th>訂單編號</th>
						<th>商品編號</th>
						<th>訂單中商品金額</th>
						<th>訂購數量</th>
					</tr>
					<%@ include file="page/page1.file"%>
					<c:forEach var="order_listVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
							<td>${order_listVO.order_no}</td>
							<td>${order_listVO.product_no}</td>
							<td>${order_listVO.order_product_pr}</td>
							<td>${order_listVO.order_quan}</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page/page2.file"%>
			</div>
		</div>
	</div>
</body>
</html>