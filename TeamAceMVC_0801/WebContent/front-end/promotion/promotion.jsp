<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchant.model.*"%>
<%@ page import="com.promotion.model.*"%>

<%-- <%=session.getAttributeNames() %> --%>

<%-- <%=session.getAttribute("merchant_no")%> --%>

<%
PromotionVO promotionVO = (PromotionVO) request.getAttribute("PromotionVO"); //Order_listServlet.java(Concroller), 存入req的empVO物件
%>


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>GuideMainPage</title>
    <style type="text/css">
    	body{
    		font-family: Microsoft JhengHei;

    	}

		.top{
			padding-top: 7.5%;
			color:#fff;
		}
		.info{
			padding-top: 20px;
			letter-spacing: 4px;
		}
    	.content{
    		margin-top: 5%;
    		height: 900px;
    		padding:80px;
			background-color: #fff;
    	}
		.right{
			margin-left:160px;
		}
    	#guide_pic{
    		height:300px;
    		width:300px;
    		border-radius: 50%;
    		border-color: #fff;
    		border: 3px solid;
    	}
    	.list-group-item{
    		color:#666;
    		padding-left: 20px;
    		letter-spacing: 2px;
    	}
		.title-2{
			line-height: 45px;
			font-family:monospace;
			font-size:18px;
			letter-spacing: 2px;
			font-weight:bold;
			color:dimgray;
			border-bottom: 1px solid #aaa;
		}
		.title{
			font-family:monospace;
			font-size:23px;
			letter-spacing: 2px;
			font-weight:bold;
			color:#666;
		}
		.lang{
			padding-top: 20px;
			padding-bottom: 20px;
			letter-spacing: 2px;
		}
		.outer{
			position: relative;
			z-index: 10;
			background-size:cover;
			background-color: #333;
		}
		#cover{
			position: fixed;
			width: 100%;
			height:auto;
			z-index: -10;
			filter: brightness(0.5);

		}
		a {
   			text-decoration: none !important;
		}
		
		#box12{
     		position: absolute;
     		left:  80%;      /* 離左邊距離*/
    		top:   -20%;     /* 位置高度 */
     		text-align: center;
     		font-size: 15px;
     		padding: 10px;
     		border: 1px solid 	#000000;  /*邊框顏色 */
     		background-color: white;
    		box-shadow: 1px 1px 5px #333333;
    		width: 400px;      /* 照片大小 */
     		transform: rotate(15deg);  /* 旋轉角度 */
     		border-style:solud;  
	 		padding-bottom:50px;  /* 下內邊距 */
   	    }
   	    
    </style>
  </head>
  <body>
<div class="outer">
	<img src="<%=request.getContextPath()%>/front-end/merchant/images/test1.jpg" id="cover">
	<div class="container">
		<div class="row justify-content-center top">
			<div>
			</div>
			<div>
				<p></p><br>
				<p></p>
				<p></p>
				<p></p>
			</div>
		</div>
	</div>
	
		<div class="row justify-content-center content">
			<div class="col-2">
	
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>選單</b></div>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantUpdate.jsp" class="list-group-item">廠商資料</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantProduct.jsp" class="list-group-item">商品</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantOrder.jsp" class="list-group-item">訂單</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantPromotion.jsp" class="list-group-item">廣告</a>
				</div>
				
			</div>
			<div class="col-5 right"">
				<p class="title">商品編號:<%=promotionVO.getProduct_no()%></p>
				<p class="title-2">廣告名稱:<%=promotionVO.getPromotion_name()%></p>
				<p class="title-2">開始日期:<%=promotionVO.getPromotion_start()%></p>
				<p class="title-2">結束日期:<%=promotionVO.getPromotion_end()%></p>	
				<p class="title-2">促銷折扣:<%=promotionVO.getPromotion_pr()%></p>	
				<p class="title-2">廣告說明:<%=promotionVO.getPromotion_ps()%></p>	
				<p class="title-2">廣告狀態:
				<c:set var="promotion_status" value="<%=promotionVO.getPromotion_status()%>"/>
				<c:if test="${promotion_status == 'B1'}">
                <%= "未審核"%>
				</c:if>
				<c:if test="${promotion_status == 'B2'}">
				<%= "上架"%>
				</c:if>
				<c:if test="${promotion_status == 'B3'}">
				<%= "下架"%>
				</c:if>
				</p>	
				<p class="lang">
				
				<div id="box12">
         			<img src="<%=request.getContextPath()%>/PromotionImageShow?promotion_no=<%=promotionVO.getPromotion_no()%>"  width="300vm"  height="200vm" >
    			</div>
			</div>
		
	</div>
</div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>