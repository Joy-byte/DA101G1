<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>

<%-- <%=session.getAttribute("merchant_no")%> --%>

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
		<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
			<div class="col-2">
	
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>選單</b></div>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantUpdate.jsp" class="list-group-item">廠商資料</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantOrder.jsp" class="list-group-item">訂單</a>
					<a href="<%=request.getContextPath()%>/front-end/order_detail/order_detail.jsp" class="list-group-item">訂單日期</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantLogin.jsp" class="list-group-item">回廠商頁面</a>
				</div>
			</div>
				
	<div class="col-5 right"">
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_detailServlet1" >
        <b>輸入訂單編號 (如2019-07-07):</b><br>
                    開始日期:<input name="order_time_start" id="f_date1" type="date"><br>
		結束日期:<input name="order_time_end" id="f_date2" type="date"><br>
        <input type="hidden" name="action" value="getOrder_detail_Day">
        <input type="hidden" name="merchant_no" value="<%=session.getAttribute("merchant_no")%>">
        <input type="submit" value="送出"> </FORM>
        
        <%if (session.getAttribute("getOrder_detail_Day")!=null){%>
		<div style="left: 100px;top: 100px;">
			<jsp:include page="order_detailOfDay.jsp" flush="true"/>
			<%-- <c:import url="<%=request.getContextPath()%>/front-end/promotion/OnlyOnePromotionOfMerchant.jsp"> --%>
			<%-- </c:import> --%>
			<%-- //動態不行用<%=request.getContextPath()%> --%>
		</div>
		<%} %>			
	</div>
</div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>