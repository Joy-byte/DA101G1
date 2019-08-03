<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.wish.list.model.*"%>
<%@ page import="com.product.controller.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="java.sql.Timestamp.*"%>
<%
	MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
%>
<%
	Wish_listService wls = new Wish_listService();
	List<Wish_listVO> wlist = wls.getMemberWish_list(memberVO.getMem_no());

	List<ProductVO> plist = new ArrayList<ProductVO>();

	for (Wish_listVO wlist2 : wlist) {
		ProductService ps = new ProductService();
		plist.add(ps.getOneProduct(wlist2.getProduct_no()));
	}
	pageContext.setAttribute("plist", plist);
%>
<%
	Order_detailService ods = new Order_detailService();
	List<Order_detailVO> odlist = ods.getMyOrder_detail(memberVO.getMem_no());

	request.setAttribute("odlist", odlist);
%>
<%
	DepositService ds = new DepositService();

	List<DepositVO> dlist = ds.getDeposit(memberVO.getMem_no());

	pageContext.setAttribute("dlist", dlist);
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

<title></title>


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
}

.tab-content {
	width: 850px;
	height: auto;
	border-left: 1px solid #dee2e6;
	border-right: 1px solid #dee2e6;
	border-bottom: 1px solid #dee2e6;
}

#nav-home-tab {
	width: 283px;
}

#nav-profile-tab {
	width: 283px;
}

#nav-contact-tab {
	width: 283px;
}

.productimg img {
	width: 80px;
	height: 80px;
	flex-grow: 1;
}

.tabledata {
	width: 300px;
	border-top: none;
	border-color: #ffffff;
	line-height:70px;
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

.orderdetail {
	margin: 50px;
}

.navbar {
	z-index: 10;
	position: fixed;
	width: 100%;
	top:0px;
}
</style>
</head>
<body>
<div>
   <%@ include file="/front-end/header.file" %>

	<div class="container">
		<nav style="margin-top:100px;">
			<div class="nav nav-tabs" id="nav-tab" role="tablist">

				<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
					aria-controls="nav-home" aria-selected="true">訂單</a> <a
					class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
					href="#nav-profile" role="tab" aria-controls="nav-profile"
					aria-selected="false">追蹤商品</a> <a class="nav-item nav-link"
					id="nav-contact-tab" data-toggle="tab" href="#nav-contact"
					role="tab" aria-controls="nav-contact" aria-selected="false">儲值</a>
			</div>
		</nav>

		<!--  訂單 -->
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
				aria-labelledby="nav-home-tab">
               <div id="getorderlist" style="padding:50px">
				<table class="table">
					<tr>
						<th style="background: #f0f0f0">編號</th>
						<th style="background: #f0f0f0">狀態</th>
						<th style="background: #f0f0f0">日期</th>
						<th style="background: #f0f0f0">金額</th>
						<th style="background: #f0f0f0"></th>
					</tr>

					<c:forEach var="order_detailVO" items="${odlist}">
						<tbody>
							<tr>
								<th scope="row">

										<button type="button" class="toorderlist"
											style="border: 0px; background-color: #fff;">
											<span> ${order_detailVO.order_no} </span>
										</button>
										<input type="hidden" name="order_no" value="${order_detailVO.order_no}"> 

								</th>
								<td>${Status.interpreter(order_detailVO.order_status)}</td>
								<td>${FormatTimestamp.getFormatTS(order_detailVO.order_time)}
								</td>
								<td>NT$${order_detailVO.order_amosum}</td>
								<td>
									<c:if test="${order_detailVO.order_status=='O1'}">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/ShoppingServlet">
											<button type="submit" class="btn btn-outline-danger">取消訂單</button>
											<input type="hidden" name="order_no"
												value="${order_detailVO.order_no}"> <input
												type="hidden" name="action" value="cancelorder">
										</FORM>
									</c:if>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
			  <div>              
                 	<a href="<%=request.getContextPath()%>/front-end/product/Shop.jsp" >
                		<button id="backtoshop" class="btn btn-secondary" Style="width:150px; margin-bottom:60px;">返回商城</button>
                	</a>
			 </div>
			</div>

			<!-- 			追蹤商品 -->
			<div class="tab-pane fade" id="nav-profile" role="tabpanel"
				aria-labelledby="nav-profile-tab">

　　　　　　　　　　<div style="padding:50px">
				<table class="table">
					<tr>
						<th style="background: #f0f0f0">商品資訊</th>
						<th style="background: #f0f0f0">單價</th>
						<th style="background: #f0f0f0"></th>
					</tr>

					<c:forEach var="productVO" items="${plist}">
						<tbody>
							<tr>
								<th scope="row" class="tab">

									<div class="productimg">
										<img src="PhotoReader3.do?product_no=${productVO.product_no}">
										${productVO.product_name}
									</div>

								</th>
								<td class="tabledata"><span>NT$</span> <span
									id="price${productVO.product_no}">${productVO.product_pr}</span></td>

								<td>
									<div>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ShoppingServlet">

											<button type="submit" class="close" aria-label="Close" Style="line-height:65px">
												<span aria-hidden="true">&times;</span>
											</button>
											<input type="hidden" name="product_no"
												value="${productVO.product_no}"> <input
												type="hidden" name="action" value="deleteFromWishList">
										</FORM>
									</div>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			 </div>
             <div>              
                 	<a href="<%=request.getContextPath()%>/front-end/product/Shop.jsp" >
                		<button class="btn btn-secondary" Style="width:150px; margin-bottom:60px;">返回商城</button>
                	</a>
			 </div>
			</div>
			<div class="tab-pane fade" id="nav-contact" role="tabpanel"
				aria-labelledby="nav-contact-tab">
			<div style="padding:50px">
				<table class="table">
					<tr>
						<th style="background: #f0f0f0">編號</th>
						<th style="background: #f0f0f0">日期</th>
						<th style="background: #f0f0f0">金額</th>
					</tr>

					<c:forEach var="depositVO" items="${dlist}">
						<tbody>
							<tr>
								<th scope="row"><span> ${depositVO.deposit_no} </span></th>
								<td><span> ${depositVO.deposit_time} </span></td>

								<td><span>${depositVO.deposit_amo}</span></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				</div>
				  <div>              
                 	<a href="<%=request.getContextPath()%>/front-end/product/Shop.jsp" >
                		<button class="btn btn-secondary" Style="width:150px; margin-bottom:60px;">返回商城</button>
                	</a>
			 </div>
			</div>
		</div>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
 	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script>
		$(document).ready(function() {
			$('.toorderlist').click(function() {
				var toorderlist = this;

				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringOrderno($(this).next().val()),
					dataType : "text",
					success : function() {
						  $("#getorderlist").load("/TeamAceMVC/front-end/product/Order_list.jsp #nav-home");
						  $('#backtoshop').toggle();
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});

		});
		function creatQueryStringOrderno(order_no) {
			var queryString = {
				"action" : "towardOrderlist",
				"order_no" : order_no
			};
			return queryString;
		}
	</script>
</body>
</html>
