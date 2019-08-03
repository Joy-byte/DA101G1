<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.productreport.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ProductreportVO productreportVO = (ProductreportVO) request.getAttribute("productreportVO"); //ProductreportServlet.java(Concroller), 存入req的productreportVO物件
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


</style>

<style>
table {
	width:600px;
	margin-top: 5px;
	margin-bottom: 5px;
}



th, td {
	padding: 5px;
	margin: 20px;
	text-align: center;
}
</style>




</head>



<body>

	<div style="display: flex; font-family: Microsoft JhengHei;">
			
			<div class="content container" style="display:flex;">

				<table style="margin-top:50px">
					<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
						<th>商品檢舉編號</th>
						<th>商品編號</th>
						<th>檢舉說明</th>
						<th>檢舉狀態</th>
					</tr>
					<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
						<td><%=productreportVO.getProductreport_no()%></td>
						<td><%=productreportVO.getProduct_no()%></td>
						<td><%=productreportVO.getReportcon_no()%></td>
						<td><c:set var="Reportcon_status"
								value="<%=productreportVO.getReportcon_status()%>" /> <c:if
								test="${Reportcon_status == 'PR1'}">
								<%="未審核"%>
							</c:if> <c:if test="${Reportcon_status == 'PR2'}">
								<%="審核通過"%>
							</c:if> <c:if test="${Reportcon_status == 'PR3'}">
								<%="審核未通過"%>
							</c:if></td>
					</tr>
				</table>
			</div>
		</div>
</body>
</html>