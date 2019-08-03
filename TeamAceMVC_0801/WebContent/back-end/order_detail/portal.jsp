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
	margin-bottom: 30px;
	background-color: #fff;
}
.setmargin{
 margin-top:10px;
 margin-bottom:10px;
}

.setinputwidth{
 width:150px;
}
.btn{
width:100px;
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
	
	<div class="main" style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/order_detail/Order_detailbar.jsp" flush="true" />
		</div>
		<div style="width:100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div style="background-color:#cf9e9e; height:50px; width:100%; line-height:50px; ">
				<h5 style="line-height:50px; margin-left:20px;">查詢訂單</h5>
			</div>

			<div class="content container" style="flex-direction:column">
              <div style="display:flex">
				<div class="status">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>//Order_detailServlet1">
						<b>輸入訂單編號 (如OR00001):</b>
						<div class="setmargin">
						 	<input class="form-control" type="text" name="order_no">
						</div>
						<input type="hidden" name="action" value="getOne_For_Order_detail">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>


				<div class="status">
					<jsp:useBean id="order_detailSvc" scope="page"
						class="com.order_detail.model.Order_detailService" />
					<jsp:useBean id="merchantSvc" scope="page"
						class="com.merchant.model.MerchantService" />
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>//Order_detailServlet1">
						<b>選擇訂單編號:</b>
						<div class="setmargin setinputwidth">
							<select class="form-control" size="1" name="order_no">
								<c:forEach var="order_detailVO" items="${order_detailSvc.all}">
									<option value="${order_detailVO.order_no}">${order_detailVO.order_no}
								</c:forEach>
							</select>
						</div>
							<input type="hidden" name="action"value="getOne_For_Order_detail"> 
							<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>

				<div class="status">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>//Order_detailServlet1">
						<b>選擇產品:</b>
						<div class="setmargin setinputwidth">
						 <select class="form-control" size="1" name="order_no">
							<c:forEach var="order_detailVO" items="${order_detailSvc.all}">
								<option value="${order_detailVO.order_no}">${order_detailVO.mem_no}
							</c:forEach>
						</select>
						</div>
						 <input type="hidden" name="action"	value="getOne_For_Order_detail"> 
						 <input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>

				<div class="status">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>//Order_detailServlet1">
						<b>選擇月份:</b>
						<div class="setmargin setinputwidth">
							 <select class="form-control" size="1" name="merchant_no">
								<c:forEach var="merchantVO" items="${merchantSvc.all}">
									<option value="${merchantVO.merchant_no}">${merchantVO.merchant_no}
								</c:forEach>
							</select> 
						</div>
						<div class="setmargin setinputwidth">
							<select class="form-control" name="month">
								<option value="01">一月</option>
								<option value="02">二月</option>
								<option value="03">三月</option>
								<option value="04">四月</option>
								<option value="05">五月</option>
								<option value="06">六月</option>
								<option value="07">七月</option>
								<option value="08">八月</option>
								<option value="09">九月</option>
								<option value="10">十月</option>
								<option value="11">十一月</option>
								<option value="12">十二月</option>
							</select> 
						</div>
						<input type="hidden" name="action"
							value="getOneMonth_One_Merchant">
						 <input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				 </div>
				</div>
				<div>
		<%
			if (request.getAttribute("order_detailVO") != null) {
		%>
		<jsp:include page="listOneOrder_detail.jsp" flush="true" />
		<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
		<%-- </c:import> --%>
		<%-- //動態不行用<%=request.getContextPath()%> --%>
		<%
			}
		%>


		<%
			if (request.getAttribute("List<Order_detailVO>") != null) {
		%>
		<jsp:include page="OneMonthOfOrder_detailByMerchant.jsp" flush="true" />
		<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
		<%-- </c:import> --%>
		<%-- //動態不行用<%=request.getContextPath()%> --%>
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
