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
.portal {
	display: flex;
}

.content {
	display: flex;
	flex-direction: column;
	width: 100%;
	background-size: cover;
	margin-bottom: 50px;
}

.status {
	flex-direction: colum;
	margin-top: 30px;
	margin-right: 15px;
	margin-left: 15px;
	margin-bottom: 30px;
	background-color: #fff;
}

.setmargin {
	margin-top: 10px;
	margin-bottom: 10px;
}

.setinputwidth {
	width: 150px;
}
.btn{
width:100px;
}
</style>
</head>
<body>
	<div class="portal" style="font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/product/Productbar.jsp" flush="true" />
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

		<div style="width: 100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div
				style="background-color: #d6d6ad; height: 50px; width: 100%; line-height: 50px;">
				<h5 style="line-height: 50px; margin-left: 20px;">查詢商品</h5>
			</div>

			<div class="content container">
				<div style="display:flex">
					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductServlet1">
							<b>輸入商品編號 (如PR00001):</b>
							<div class="setmargin">
								<input class="form-control" type="text" name="product_no">
							</div>
							<input type="hidden" name="action" value="getOne_For_Display">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>


					<div class="status">
						<jsp:useBean id="productSvc" scope="page"
							class="com.product.model.ProductService" />
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductServlet1">
							<b>選擇商品編號:</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" size="1" name="product_no">
									<c:forEach var="productVO" items="${productSvc.all}">
										<option value="${productVO.product_no}">${productVO.product_no}
									</c:forEach>
								</select>
							</div>
							<input type="hidden" name="action" value="getOne_For_Display">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>

					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductServlet1">
							<b>選擇廠商編號:</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" size="1" name="product_no">
									<c:forEach var="productVO" items="${productSvc.all}">
										<option value="${productVO.product_no}">${productVO.merchant_no}
									</c:forEach>
								</select>
							</div>
							<input type="hidden" name="action" value="getOne_For_Display">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>

					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductServlet1">
							<b>選擇商品狀態:</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" name="product_status">
									<option value="C0">下架</option>
									<option value="C1">上架</option>
								</select>
							</div>
							<input type="hidden" name="action" value="get_OneStatus_Product">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>

					<div class="status">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ProductServlet1">
							<b>選擇商品狀態:</b>
							<div class="setmargin setinputwidth">
								<select class="form-control" name="product_status">
									<option value="C0">下架</option>
									<option value="C1">上架</option>
								</select>
							</div>
							<b>選擇廣告狀態:</b>
							<div class="setmargin">
								<select class="form-control" name="product_pn">
									<option value="D0">沒有廣告促銷狀態</option>
									<option value="D1">有廣告促銷狀態</option>
								</select>
							</div>
							<input type="hidden" name="action" value="get_TwoStatus_Product">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</FORM>
					</div>
				</div>
				<div>
					<%
						if (request.getAttribute("productVO") != null) {
					%>
					<jsp:include page="listOneProduct.jsp" flush="true" />
					<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
					<%-- </c:import> --%>
					<%-- //動態不行用<%=request.getContextPath()%> --%>
					<%
						}
					%>

					<%
						if (request.getAttribute("get_OneStatus_Product") != null) {
					%>
					<jsp:include page="listOneStatusOfProduct.jsp" flush="true" />
					<%-- 				<c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
					<%-- 				</c:import> --%>
					<%-- 				//動態不行用<%=request.getContextPath()%> --%>
					<%
						}
					%>


					<%
						if (request.getAttribute("get_TwoStatus_Product") != null) {
					%>
					<jsp:include page="listTwoStatusOfProduct.jsp" flush="true" />
					<%-- 				<c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
					<%-- 				</c:import> --%>
					<%-- 				//動態不行用<%=request.getContextPath()%> --%>
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
