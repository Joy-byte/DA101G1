<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.wish.list.model.*"%>
<%@ page import="com.product.controller.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="java.sql.Timestamp.*"%>
<%@ page import="com.order_list.model.*"%>

<%
	List<Order_listVO> ollist = (List<Order_listVO>) session.getAttribute("order_list");
	ProductService ps = new ProductService();
	List<ProductVO> oplist = new ArrayList<ProductVO>();

	for (Order_listVO ollist2 : ollist) {
		ProductVO productVO = ps.getOneProduct(ollist2.getProduct_no());
		oplist.add(productVO);
	}

	pageContext.setAttribute("oplist", oplist);
%>

<%
	List<Order_detailVO> odlist = (List<Order_detailVO>) session.getAttribute("order_detail");
%>
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

<title>OrderList</title>


<style>
body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}
.container {
	width: 850px;
	height: auto;
	padding-left: 0px;
	padding-right: 0px;
	text-align: center;
	border: 1px solid #dee2e6;
}

.productimg img {
	margin-left: -80px;
	width: 80px;
	height: 80px;
	flex-grow: 1;
	flex-direction: colum;
	width: 80px;
}

.tabledata {
	width: 300px;
	border-top: none;
	border-color: #ffffff;
}

#tablehead {
	border-top: none;
}

.card-header {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: space-around;
}

#infosection {
	flex-direction: colum;
	text-align: left;
	flex-wrap: wrap;
	justify-content: space-around;
}


.navbar {
	z-index: 10;
	position: fixed;
	width: 100%;
}
</style>
</head>
<body>
 <%@ include file="/front-end/header.file" %>
  
	<div class="container">
		
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-home"
				role="tabpanel" aria-labelledby="nav-home-tab">

				<div class="thead" Style="display: flex; justify-content: space-around; height: 50px; width: 750px; line-height: 50px; background-color: #f0f0f0;">
					<span>商品資訊</span> <span>單價</span> <span>數量</span>
				</div>

				<div class="showinflex2" Style="width: 745px;display: flex;">
					<div class="showincolum" Style="width: 320px;">
						<c:forEach var="productVO" items="${oplist}">
							<div class="productimg" Style="margin-top:15px">
								<img src="PhotoReader3.do?product_no=${productVO.product_no}">
								${productVO.product_name}
							</div>
							<hr>
						</c:forEach>
					</div>
					<div class="showinflex" Style="width: 250px;">
						<c:forEach var="order_listVO" items="${order_list}">
							<div class="resize" style="height: 80px;line-height: 80px; margin-top:15px">NT$ ${order_listVO.order_product_pr}</div>
							<hr>
						</c:forEach>
					</div>
					<div class="showinflex" Style="width: 250px;">
						<c:forEach var="order_listVO" items="${order_list}">
							<div class="resize" style="height: 80px;line-height: 80px; margin-top:15px">${order_listVO.order_quan}</div>
							<hr>
						</c:forEach>

					</div>
				</div>

				<div id="infosection" >
					<c:forEach var="order_detailVO" items="${order_detail}">
						<div class="card" style="border:none; margin-top:50px">
							<div class="card-header" style="background-color: #f0f0f0;">訂單明細</div>
							<ul class="list-group list-group-flush" Style="text-align: left">
								<li class="list-group-item">編號:&nbsp${order_detailVO.order_no}</li>
								<li class="list-group-item">訂購時間:&nbsp${FormatTimestamp.getFormatTST(order_detailVO.order_time)}</li>
								<li class="list-group-item">收件人姓名:&nbsp${order_detailVO.order_cusname}</li>
								<li class="list-group-item">收件人電話:&nbsp${order_detailVO.order_cusphone}</li>
								<li class="list-group-item">送貨地址:&nbsp${order_detailVO.order_cusadr}</li>
								<li class="list-group-item">消費金額:&nbsp${order_detailVO.order_amosum}&nbspyo</li>
							</ul>
						</div>
					</c:forEach>
				</div>
				 <div>              
                 	<a href="<%=request.getContextPath()%>/front-end/product/Info.jsp" >
                		<button class="btn btn-secondary" Style="width:150px; margin-top:10%;">返回</button>
                	</a>
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
