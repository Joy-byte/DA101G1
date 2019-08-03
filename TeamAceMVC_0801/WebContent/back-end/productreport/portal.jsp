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
	margin-top: 30px;
	margin-right: 15px;
	margin-left: 15px;
}

.setmargin {
	margin-top: 10px;
	margin-bottom: 10px;
}

.setinputwidth {
	width: 150px;
}
</style>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="main"
		style="display: flex; font-family: Microsoft JhengHei;">
		<div class="logo" style="margin-right: 30px;">
			<img src="<%=request.getContextPath()%>/back-end/portal/icon.jpeg"
				id="logo"
				style="width: 100px; height: 100px; margin-left: 70px; margin-top: 30px; margin-bottom: 30px;">

			<ul class="list-group list-group-flush"
				style="margin-left: 30px; width: 200px; text-align: center;">
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/productreport/portal.jsp'>查詢</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/productreport/listAllProductreport.jsp'>全部</a></li>
				<li class="list-group-item"><a style="text-decoration:none; color:black"
					href="<%=request.getContextPath()%>/back-end/guidereport/GuideReport.jsp">旅伴檢舉</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/portal/portal.jsp'>回首頁</a></li>
			</ul>
		</div>
		<div style="width: 100%;">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />

			<div
				style="background-color: #eee8aa; height: 50px; width: 100%; line-height: 50px">
				<h5 style="line-height: 50px; margin-left: 20px">查詢商品檢舉</h5>
			</div>
			<div class="content" style="display:flex; flex-direction:column; justify-content:center" >
				<div class="container" style="display:flex">
					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductreportServlet1">
							<b>輸入檢舉編號 (如PR00001):</b>
							<div class="setmargin ">
								<input class="form-control" type="text" name="productreport_no">
							</div>
							<input type="hidden" name="action"
								value="getOne_For_Productreport"> <input
								class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>


					<div class="status">
						<jsp:useBean id="productreportSvc" scope="page"
							class="com.productreport.model.ProductreportService" />
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductreportServlet1">
							<b>選擇檢舉編號:</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" size="1" name="productreport_no">
									<c:forEach var="productreportVO"
										items="${productreportSvc.all}">
										<option value="${productreportVO.productreport_no}">${productreportVO.productreport_no}
									</c:forEach>
								</select>
							</div>
							<input type="hidden" name="action"
								value="getOne_For_Productreport"> <input
								class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>

					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductreportServlet1">
							<b>選擇商品編號::</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" size="1" name="productreport_no">
									<c:forEach var="productreportVO"
										items="${productreportSvc.all}">
										<option value="${productreportVO.productreport_no}">${productreportVO.product_no}
									</c:forEach>
								</select>
							</div>
							<input type="hidden" name="action"
								value="getOne_For_Productreport"> <input
								class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>
				</div>
				<div>
					<%
						if (request.getAttribute("productreportVO") != null) {
					%>
					<jsp:include page="listOneProductreport.jsp" flush="true" />
					<%
						}
					%>
				</div>
			</div>
		</div>
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
