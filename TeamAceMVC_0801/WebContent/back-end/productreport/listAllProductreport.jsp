<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productreport.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ProductreportService productreportSvc = new ProductreportService();
	List<ProductreportVO> list = productreportSvc.getAll();
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
.content {
	display: flex;
	width: 100%;
	background-size: cover;
	margin-bottom: 50px;
	padding: 0;
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
	margin: 20px;
	margin-bottom: 5px;
}
</style>


</head>

<body>
	<div class="main"
		style="display: flex; font-family: Microsoft JhengHei;">
		<div class="logo" style="margin-right: 30px;">
			<img
				src="<%=request.getContextPath()%>/back-end/productreport/icon.jpeg"
				style="width: 100px; height: 100px; margin-left: 70px; margin-top: 30px; margin-bottom: 30px;">

			<ul class="list-group list-group-flush "
				style="margin-left: 30px; width: 200px; text-align: center;">
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/productreport/portal.jsp'>查詢</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/productreport/listAllProductreport.jsp'>全部</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href="<%=request.getContextPath()%>/back-end/guidereport/GuideReport.jsp">旅伴檢舉</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/portal/portal.jsp'>回首頁</a></li>
			</ul>
		</div>
		<div style="width: 100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div
				style="background-color: #eee8aa; height: 50px; width: 100%; line-height: 50px">
				<h5 style="line-height: 50px; margin-left: 20px">所有商品檢舉</h5>
			</div>

			<div class="content container"
				style="display: flex; flex-direction: column;">



				<table style="margin-top: 50px">
					<tr
						style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
						<th>商品檢舉編號</th>
						<th>商品編號</th>
						<th>檢舉說明</th>
						<th>檢舉狀態</th>
						<th></th>
						<th>選擇檢舉狀態:</th>
					</tr>
					<%@ include file="page/page1.file"%>
					<c:forEach var="productreportVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr
							style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
							<td>${productreportVO.productreport_no}</td>
							<td>${productreportVO.product_no}</td>
							<td>${productreportVO.reportcon_no}</td>
							<td><c:set var="reportcon_status"
									value="${productreportVO.reportcon_status}" /> <c:if
									test="${reportcon_status == 'PR1'}">
									<%="未審核"%>
								</c:if> <c:if test="${reportcon_status == 'PR2'}">
									<%="審核後通過"%>
								</c:if> <c:if test="${reportcon_status == 'PR3'}">
									<%="審核後不通過"%>
								</c:if></td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ProductServlet1"
									style="margin-bottom: 0px;">
									<input class="btn btn-outline-secondary" type="submit"
										value="商品明細"> <input type="hidden" name="product_no"
										value="${productreportVO.product_no}"> <input
										type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
							<td>

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ProductreportServlet1"
									style="margin-bottom: 0px;">

									<div style="display: flex">
										<div>
											<select class="form-control" name="reportcon_status"
												style="width: 110px">
												<option value="PR2">審核通過</option>
												<option value="PR3">審核未通過</option>
											</select>
										</div>
										<input type="hidden" name="action"
											value="getOne_Status_Update"> <input type="hidden"
											name="productreport_no"
											value="${productreportVO.productreport_no}"> <input
											type="hidden" name="product_no"
											value="${productreportVO.product_no}">
										<div>
											<input class="btn btn-outline-secondary" type="submit"
												value="送出">
										</div>
									</div>
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page/page2.file"%>
			</div>
		</div>
	</div>
</body>
</html>