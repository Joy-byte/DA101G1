<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%-- <%=session.getAttribute("merchant_no")%> --%>
<%
		List<Order_detailVO> list = (List<Order_detailVO>) session.getAttribute("get_OneStatus_Order_detail"); //EmpServlet.java(Concroller), 存入req的merchantVO物件
		pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
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
    
<style>
  table {
	width: 918px;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
  }
  th, td {
    padding: 5px;
    text-align: center;
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
			<div class="col-2" style="top:375px;left:350px;position:absolute;">
				<div class="list-group list-group-flush">
					<div class="list-group-item" style="font-family:monospace;font-size: 18px;"><b>選單</b></div>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantUpdate.jsp" class="list-group-item">廠商資料</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantOrder.jsp" class="list-group-item">訂單</a>
					<a href="<%=request.getContextPath()%>/front-end/merchant/Index/MerchantLogin.jsp" class="list-group-item">回廠商頁面</a>
					
				</div>			
			</div>
<div style="top:375px;left:800px;position:absolute;">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_detailServlet1" >
       <b>選擇訂單狀態:</b>
			<select name="order_status">
　				<option value="O1">撿貨</option>
　				<option value="O2">出貨</option>
　				<option value="O3">取消訂單</option>
				<option value="O4">退貨</option>
			</select>
       <input type="hidden" name="action" value="get_OneStatus_Order_detail">
       <input type="hidden" name="merchant_no" value="<%=session.getAttribute("merchant_no")%>">
       <input type="submit" value="送出">
</FORM>



<table style="border-top:3px #4682B4 solid;border-bottom:3px #4682B4 solid;">
	<tr style="border-top:3px #4682B4 solid;border-bottom:3px #4682B4 solid;">
		<th>訂單編號</th>
		<th>會員編號</th>
		<th style=" width: 100px;">訂單狀態</th>
		<th style=" width: 100px;">訂單總金額</th>
		<th>訂單時間</th>
		<th>取貨地址</th>
		<th>收件人姓名</th>
		<th>收件人電話</th>
		<th></th>
        <th></th>
	</tr>
	<%@ include file="page/page1.file" %>
	<c:forEach var="order_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
<%-- 			<td>${order_detailVO.order_no}</td> --%>
			<td><A href="<%=request.getContextPath()%>/Order_listServlet1?order_no=${order_detailVO.order_no}&merchant_no=<%=session.getAttribute("merchant_no")%>&action=getOne_From05">${order_detailVO.order_no}</a></td>
			<td>${order_detailVO.mem_no}</td>
			<td>
			<c:set var="order_status" value="${order_detailVO.order_status}"/>
				<c:if test="${order_status == 'O1'}">
                <%= "撿貨"%>
				</c:if>
				<c:if test="${order_status == 'O2'}">
				<%= "已出貨"%>
				</c:if>
				<c:if test="${order_status == 'O3'}">
				<%= "取消訂單"%>
				</c:if>
				<c:if test="${order_status == 'O4'}">
				<%= "退貨"%>
				</c:if>
			</td>
			<td>${order_detailVO.order_amosum}</td>
			<td><fmt:formatDate value="${order_detailVO.order_time}" pattern="yyyy-MM-dd"/></td> 
			<td>${order_detailVO.order_cusadr}</td>
			<td>${order_detailVO.order_cusname}</td>
			<td>${order_detailVO.order_cusphone}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_listServlet1" style="margin-bottom: 0px;">
			     <input type="submit" value="明細">
			     <input type="hidden" name="order_no"  value="${order_detailVO.order_no}">
			     <input type="hidden" name="action"	value="getOne_For_UpdateFromOrder_detail"></FORM>		
			</td>
			<td>
			<c:if test="${order_status == 'O1'}">
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_detailServlet1" style="margin-bottom: 0px;">
			     <input type="submit" value="出貨">
			     <input type="hidden" name="order_no"  value="${order_detailVO.order_no}">
			     <input type="hidden" name="order_status"  value="O2">
			     <input type="hidden" name="action"	value="getOneOrder_detailStatus_Update"></FORM>
			</c:if>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>


</body>
</html>