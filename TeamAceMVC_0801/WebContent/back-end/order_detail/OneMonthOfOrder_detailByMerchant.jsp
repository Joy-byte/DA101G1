<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.order_list.model.*"%>

<%
		List<Order_detailVO> list = (List<Order_detailVO>) request.getAttribute("List<Order_detailVO>"); //EmpServlet.java(Concroller), 存入req的merchantVO物件
		pageContext.setAttribute("list",list);
%>

<%
	Order_detailService order_detailSvc = new Order_detailService();
	Order_detailVO order_detailVO = order_detailSvc.getOneMonthOfMerchant((String)(session.getAttribute("month")), (String)(session.getAttribute("merchant_no")));
	pageContext.setAttribute("order_detailVO", order_detailVO);
	
	Order_listService order_listSvc = new Order_listService();
	Order_listVO order_listVO = order_listSvc.getOneMonthOfMerchant((String)(session.getAttribute("month")), (String)(session.getAttribute("merchant_no")));
	pageContext.setAttribute("order_listVO", order_listVO);

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
  table {
	width: 1000px;
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



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div style="font-family: Microsoft JhengHei;">

<div class="content container" style=" display: flex;flex-direction: column; ">
<span style="font-size:0.5cm;">廠商編號:<%=session.getAttribute("merchant_no")%></span><br>
<div style="display:flex; margin-bottom:20px;">
	<span style="font-size:20pt;"><%=session.getAttribute("month")%></span>
	<span style="font-size:20pt;">月份訂單總額&nbsp<%=order_detailVO.getOrder_amosum()%>元</span><br>
</div>
<table>
	<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
		<th>訂單編號</th>
		<th>會員編號</th>
<!-- 		<th>廠商編號</th> -->
		<th>訂單狀態</th>
		<th>訂單總金額</th>
		<th>訂單時間</th>
		<th>取貨地址</th>
		<th>收件人姓名</th>
		<th>收件人電話</th>
		<th></th>
	</tr>
<%@ include file="page/page1.file" %> 
	<c:forEach var="order_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
			<td>${order_detailVO.order_no}</td>
			<td>${order_detailVO.mem_no}</td>
<%-- 			<td>${order_detailVO.merchant_no}</td> --%>
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
			     <input class="btn btn-outline-secondary" type="submit" value="明細">
			     <input type="hidden" name="order_no"  value="${order_detailVO.order_no}">
			     <input type="hidden" name="action"	value="getOne_For_UpdateFromOrder_detail"></FORM>
				
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>
</div>
<span style="font-size:20pt;">該名廠商月份訂單總額&nbsp<%=order_listVO.getOrder_product_pr()%>元</span><br>
</div>


</body>
</html>