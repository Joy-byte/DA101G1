<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.promotion.model.*"%>
<%
	ProductService ps = new ProductService();
	List<ProductVO> list = ps.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
%>


<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title>Shop</title>
<style type="text/css">
body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}

.producttype {
	top: 55px;
	z-index: 3;
	position: fixed;
	height: 55px;
	width: 100%;
	text-align: center;
	background-color: rgba(255, 255, 255, 0.8);
	font-color: #AAAAAA;
	line-height: 55px;
}

.product {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	justify-content: flex-start;
	margin-left: 270px;
	margin-right: 250px;
	margin-bottom: 50px;
}

.card {
	margin-top: 50px;
	margin-right:10px;
	margin-left:10px;
}

.card-img-top {
	height: 280px;
	width: 318px;
}

.card-body {
	font-size: 150%;
	text-align: center;
}

.container img {
	height: 750px;
	width: 900px;
}

.productimg img {
	width: 80px;
	height: 80px;
	flex-grow: 1;
}

.productincart {
	z-index: 9999;
	top: 0px;
	position: fixed;
	height: 100%;
	width: 300px;
	margin-left: -300px;
	background-color: #ffffff;
	border: 3px solid #f8f9fa;
}

.qty {
	width: 38px;
	height: 38px;
	text-align: center;
	border: 0;
}

.plus {
	width: 38px;
	height: 38px;
	border: 1px solid #aaa;
	background: none;
}

.minus {
	width: 38px;
	height: 38px;
	border: 1px solid #aaa;
	background: none;
}

.productdetail {
	justify-content: colum;
	margin-left: 10px;
}

.clickbuy {
	width: 65px;
	height: 65px;
	border-radius: 40px;
	border: 1px solid #95A5A6;
	text-align: center;
	line-height: 65px;
	z-index: 100;
	margin-left: 240px;
	margin-top: 205px;
	position: absolute;
	background-color: #ffffff;
	visibility: hidden;
}

.cartimg {
	width: 45px;
	height: 45px;
}

.card-img-model {
	width: 400px;
	height: 400px;
}

.add {
	background: #fff;
}

.modal-body {
	display: flex;
}

.modalimg {
	width: 20px;
	height: 20px;
	margin-left: 10px;
}

.productbtn {
	padding: 0px;
	border: 0px;
	background-color: #fff;
}

#tinycart {
	top: 700px;
	position: fixed;
	left: 0px;
	background-color: #fff;
	width: 70px;
	height: 70px;
	z-index: 1000;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
}

#member {
	top: 600px;
	position: fixed;
	left: 0px;
	background-color: #fff;
	width: 70px;
	height: 70px;
	z-index: 1000;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
}

#deposit {
	top: 500px;
	position: fixed;
	left: 0px;
	background-color: #fff;
	width: 70px;
	height: 70px;
	z-index: 1000;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
}

.btngroup1 {
	display: flex;
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

	<div class="producttype">
		<span class="col-12 col-sm-4">-&nbsp&nbsp農漁產品&nbsp&nbsp</span> <span
			class="col-12 col-sm-2">-&nbsp&nbsp精緻伴手禮&nbsp&nbsp</span> <span
			class="col-12 col-sm-2">-&nbsp&nbsp文創小物&nbsp&nbsp</span> <span
			class="col-12 col-sm-4">-&nbsp&nbsp在地服務&nbsp&nbsp</span>
	</div>

	<!-- 購物車 -->
	<c:set var="buylist" value="${shoppingcart}" />
	<div class="productincart" id="shoppingcart">
		<div id="newcart">
			<c:forEach var="productVO" items="${shoppingcart}">
				<table class="table">
					<tbody>
						<tr>
							<th scope="row">
								<div class="productimg">
									<img src="PhotoReader3.do?product_no=${productVO.product_no}">
								</div>
							</th>
							<td>
								<div>${productVO.product_name}</div>
								<div>NT$${productVO.product_pr} X
									${productVO.product_quan}</div>
							</td>
							<td>
								<div>
									<button type="button" class="close deletefromcart"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<input type="hidden" name="product_no"
										value="${productVO.product_no}">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			<c:if test="${empty buylist}">
				<div id="vacant" style="line-height:800px; text-align:center">購物車目前沒有商品</div>
			</c:if>
			<c:if test="${!empty buylist}">
			   <div>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/ShoppingServlet">
						<button type="submit" class="btn btn-dark" style="text-align: center; width: 280px; margin-left: 10px;">結帳去</button>
						<input type="hidden" name="action" value="towardCart">
					</FORM>
			   </div>
			</c:if>
		</div>
	</div>

	<div id="deposit">
		<button type="button"
			style="height: 70px; width: 70px; background-color: #fff; border: none; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border: 1px solid #95A5A6;">
			<img
				src="<%=request.getContextPath()%>/front-end/product/images/deposit.png"
				style="width: 45px; height: 45px;"
				onclick="location.href='<%=request.getContextPath()%>/front-end/deposit/addDeposit2.jsp'">
		</button>
	</div>

	<div id="member">
		<a href="<%=request.getContextPath()%>/front-end/product/Info.jsp">
			<button type="button"
				style="height: 70px; width: 70px; background-color: #fff; border: none; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border: 1px solid #95A5A6;">
				<img
					src="<%=request.getContextPath()%>/front-end/product/images/member.png"
					style="width: 60px; height: 60px;">
			</button>
		</a>
	</div>

	<div id="tinycart">
		<button type="button" id="btn3"
			style="height: 70px; width: 70px; outline: none; background-color: #fff; border: none; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border: 1px solid #95A5A6;">
			<img
				src="<%=request.getContextPath()%>/front-end/product/images/shoppingcart.png"
				style="width: 55px; height: 55px;">
		</button>
	</div>


	<!-- 廣告牆 -->

	<div class="container">
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="PhotoReader3.do?product_no=PR00001" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="PhotoReader3.do?product_no=PR00002" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="PhotoReader3.do?product_no=PR00003" class="d-block w-100"
						alt="...">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<!-- 商品 -->

	<jsp:useBean id="promoSvc" scope="page"
		class="com.promotion.model.PromotionService" />

	<div class="product">
		<c:forEach var="productVO" items="${list}">
			<div class="card" style="width: 20rem; height: 27rem">
				<button type="button" class="productbtn" data-toggle="modal"
					data-target=".bd-example-modal-lg${productVO.product_no}">
					<img src="PhotoReader3.do?product_no=${productVO.product_no}"
						class="card-img-top"
						onmouseover="show1('${productVO.product_no}')"
						onmouseleave="show2('${productVO.product_no}')">
				</button>

				<!-- 處理打折過後的商品價格 -->
				<c:set var="balance"
					value="${promoSvc.findByProductNo(productVO.product_no).promotion_pr * productVO.product_pr}" />
				<fmt:parseNumber var="i" type="number" value="${balance}" />


				<div class="card-body">
					<h5 class="card-title">${productVO.product_name}</h5>
					<p class="card-text">
						<c:if test="${productVO.product_pn=='D1'}">
							<span> <font color="#ff8f59">NT$<c:out value="${i}" /></font></span>
							<br>
							<span style="text-decoration: line-through;"> <font
								color="#bebebe">NT$${productVO.product_pr}</font></span>
						</c:if>
						<c:if test="${productVO.product_pn=='D0'}">
							<span>NT$${productVO.product_pr}</span>
						</c:if>
					</p>
				</div>
				<!-- 商品圖購物車圖示 -->
				<div class="clickbuy" id="clicktobuy${productVO.product_no}">
					<a id="add" class="buy" value="addToCart"> <img
						src="<%=request.getContextPath()%>/front-end/product/images/shoppingcart.png"
						class="cartimg" onmouseover="show1('${productVO.product_no}')"
						onmouseleave="show2('${productVO.product_no}')">
					</a> <input type="hidden" name="product_no"
						value="${productVO.product_no}"> <input type="hidden"
						name="product_quan" value="${productVO.product_quan}">
				</div>
			</div>

			<!--查看單一商品-->
			<div class="modal fade bd-example-modal-lg${productVO.product_no}"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalCenterTitle"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<div class="modal-body">
							<div>
								<img src="PhotoReader3.do?product_no=${productVO.product_no}"
									class="card-img-model" alt="...">
							</div>
							<div class="productdetail">
								<div>
									<h2>${productVO.product_name}</h2>
									<c:if test="${productVO.product_pn=='D1'}">
										<div Style="display: flex">
											<h3>
												<font color="#ff8f59">NT$<c:out value="${i}" /></font>


											</h3>
											&nbsp&nbsp
											<h3>
												<span style="text-decoration: line-through"> <font
													color="#bebebe">NT$${productVO.product_pr}</font>
												</span>
											</h3>
										</div>
									</c:if>
									<c:if test="${productVO.product_pn=='D0'}">
										<h2>NT$${productVO.product_pr}</h2>
									</c:if>
								</div>
								<div>

									<input class="changequan minus" type='button'
										id="minus${productVO.product_no}"
										onclick="runMinus('${productVO.product_no}')"
										name='${productVO.product_no}' value='-' field='quantity' />
									<input class='qty' type='text' id="qty${productVO.product_no}"
										name='quantity' value='${productVO.product_quan}' /> <input
										type='button' onclick="runPlus('${productVO.product_no}')"
										name='${productVO.product_no}' value='+'
										class='changequan plus' field='quantity' />
								</div>
								<div class="btngroup1">
									<div>
										<button type="button"
											class="btn btn-outline-secondary towishlist"
											style="width: 150px; margin-right: 10px; margin-top: 10px">
											加入追蹤</button>
										<input type="hidden" name="product_no"
											value="${productVO.product_no}">
									</div>
									<div>
										<button type="button" id="add"
											class="btn btn-outline-info buy" value="addToCart"
											style="width: 150px; margin-right: 10px; margin-top: 10px">
											加入購物車</button>
										<input type="hidden" name="product_no"
											value="${productVO.product_no}"> <input type="hidden"
											id="proquan${productVO.product_no}" name="product_quan"
											value="${productVO.product_quan}">
									</div>
									<div class="report">
										<button class="productbtn" type="button"
											data-target="#report${productVO.product_no}"
											data-toggle="modal"
											style="margin-right: 5px; margin-top: 10px">
											<img
												src="<%=request.getContextPath()%>/front-end/product/images/flag.png"
												class="modalimg">
										</button>
									</div>
								</div>
								<hr style="width: 180%">
								<div>
									商品說明<br> ${productVO.product_ps}
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 商品檢舉 -->

			<div class="modal fade " id="report${productVO.product_no}"
				tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">檢舉${productVO.product_name}</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body"
							style="display: flex; flex-direction: column">
							<label for="message-text" class="col-form-label">事由</label>
							<textarea class="form-control textarea" id="message-text"
								value="" name="reportcon_no"></textarea>
							<button type="button"
								class="btn btn-outline-secondary reportproduct"
								data-dismiss="modal"
								style="margin-top: 20px; width: 20%; margin-left: 80%">送出</button>
							<input type="hidden" name="product_no"
								value="${productVO.product_no}">
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		function runPlus(id) {
			var qty = document.getElementById('qty' + id);
			var proquan = document.getElementById('proquan' + id);
			var qtyNum = parseInt(qty.value);

			qty.value = qtyNum + 1;
			proquan.value = qtyNum + 1;
			qtyNum = parseInt(qty.value);

			if (qtyNum > 1) {
				document.getElementById('minus' + id).disabled = false;
			}

		}
		function runMinus(id) {
			var qty = document.getElementById('qty' + id);
			var proquan = document.getElementById('proquan' + id);
			var qtyNum = parseInt(qty.value);

			qty.value = qtyNum - 1;
			proquan.value = qtyNum - 1;
			qtyNum = parseInt(qty.value);

			if (qtyNum <= 1) {
				document.getElementById('minus' + id).disabled = true;
			}

		}
		function show1(id) {

			var icon = document.getElementById('clicktobuy' + id);
			icon.style.visibility = 'visible';
		}

		function show2(id) {
			var icon = document.getElementById('clicktobuy' + id);
			icon.style.visibility = 'hidden';
		}

		$(document).ready(function() {

			$("#btn3").click(function() {
				$("#tinycart").animate({
					left : "300px"
				});
				$("#shoppingcart").animate({
					left : "300px"
				});
			});

		});
		
		$("#btn3").click(function(event) {
			event.stopPropagation();
		});
			
		$(document).click(function() {
			$("#tinycart").animate({
				left : "0px"
			});
			$("#shoppingcart").animate({
				left : "-300px"
			});

		});

		$(document).ready(function() {
			  $('.buy').click(function() {
					$.ajax({
						type : "GET",
						url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					    data : creatQueryString($(this).next().val(),$(this).next().next().val()),
					    dataType : "text",
						success : function() {
							$("#shoppingcart").load("/TeamAceMVC/front-end/product/Shop.jsp #shoppingcart #newcart");
							$("#tinycart").animate({
								left : "300px"
						    });
							$(".buy").click(function(event) {
							     event.stopPropagation();
					        });
							$("#shoppingcart").animate({
								left : "300px"
				});

			 },
						error : function() {
							alert("AJAX發生錯誤囉!")
		  }
	   });
    });
});

		function creatQueryString(product_no, product_quan) {
			var queryString = {
				"action" : "addToCart",
				"product_no" : product_no,
				"product_quan" : product_quan
			};
			return queryString;
		}

// 		加入追蹤
		
		$(document).ready(function() {
			$('.towishlist').click(function() {
				var towishlist = this;

				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringWishlist($(this).next().val()),
					dataType : "text",
					success : function() {
                      alert("加入成功")
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});

		});
		function creatQueryStringWishlist(product_no) {
			var queryString = {
				"action" : "insertIntoWishList",
				"product_no" : product_no
			};
			return queryString;
		}
		
//從購物車移除商品

			$(document).on('click', '.deletefromcart', function(event) {
				
				var deletefromcart = this;
				event.stopPropagation();
				
				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringDelete($(this).next().val()),
					dataType : "text",
					success : function() { 
						
					$("#shoppingcart").load("/TeamAceMVC/front-end/product/Shop.jsp #shoppingcart #newcart");
			
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});	
		function creatQueryStringDelete(product_no) {
			var queryString = {
				"action" : "deleteFromCart",
				"product_no" : product_no
			};
			return queryString;
		}
		
// 商品檢舉		
		$(document).ready(function() {
			$('.reportproduct').click(function() {
				var reportproduct = this;

				$.ajax({
					type : "GET",
					url : "/TeamAceMVC/front-end/ShoppingServlet.do",
					data : creatQueryStringReport($(this).next().val(),$(this).prev().val()),
					dataType : "text",
					success : function() {
					},
					error : function() {
						alert("AJAX-grade發生錯誤囉!")
					}
				});
			});

		});
		function creatQueryStringReport(product_no,reportcon_no) {
			var queryString = {
				"action" : "reportproduct",
				"product_no" : product_no,
				"reportcon_no" : reportcon_no
			};
			return queryString;
		}
		
		
		$(document).ready(function() {
			 if($('#isLogin').attr('name')!=""){
				 $('#isLogin').text('登出');
				 $('#isLogin').prev().attr('onclick','logoutbtns()');
			 }else{
				 $('#isLogin').text('登入');
				 $('#isLogin').prev().attr('onclick','loginbtns()');
			 }
		});

		function loginbtns(){
			location.href="<%=request.getContextPath()%>/front-end/member/login.jsp";
		}
	</script>
</body>
</html>





