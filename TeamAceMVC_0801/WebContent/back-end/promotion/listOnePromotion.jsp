<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.promotion.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PromotionVO promotionVO = (PromotionVO) request.getAttribute("promotionVO"); //PromotionServlet.java(Concroller), 存入req的promotionVO物件
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
	width: auto;
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


<div class="content">

<table style="margin-top:50px">
	<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
		<th style=" width: 100px;">廣告編號</th>
		<th>廠商編號</th>
		<th>商品編號</th>
		<th style="width:200px">廣告名稱</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>促銷折扣</th>
		<th style="width:350px">廣告說明</th>
		<th>廣告狀態</th>
		<th>廣告圖檔</th>
	</tr>
	<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
		<td style=" width: 100px;"><%=promotionVO.getPromotion_no()%></td>
		<td><%=promotionVO.getMerchant_no()%></td>
		<td style=" width: 100px;"><%=promotionVO.getProduct_no()%></td>
		<td style=" width: 100px;"><%=promotionVO.getPromotion_name()%></td>
		<td><%=promotionVO.getPromotion_start()%></td>
		<td><%=promotionVO.getPromotion_end()%></td>
		<td><%=promotionVO.getPromotion_pr()%></td>
		<td><%=promotionVO.getPromotion_ps()%></td>
		<td>
		<c:set var="Promotion_status" value="<%=promotionVO.getPromotion_status()%>"/>
		<c:if test="${Promotion_status == 'B1'}">
                	<%= "未審核"%>
		</c:if>
		<c:if test="${Promotion_status == 'B2'}">
					<%= "正常"%>
		</c:if>
		<c:if test="${Promotion_status == 'B3'}">
					<%= "下架"%>
		</c:if>
		</td>
		<td><img src="<%=request.getContextPath()%>/PromotionImageShow?promotion_no=${promotionVO.promotion_no}" width="160vm"  height="120vm"></td>
	</tr>
</table>

</div>

</body>
</html>