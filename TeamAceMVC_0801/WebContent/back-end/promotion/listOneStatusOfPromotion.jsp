<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

 <%	 
 	List<PromotionVO> list = (List<PromotionVO>) request.getAttribute("List<PromotionVO>"); //EmpServlet.java(Concroller), 存入req的merchantVO物件
	pageContext.setAttribute("list",list);
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



<body bgcolor='white'>

<div class="content" style=" display: flex;flex-direction: column; ">
<table style="margin-top:50px">
	<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
		<th style=" width: 100px;">廣告編號</th>
		<th>廠商編號</th>
		<th style=" width: 100px;">商品編號</th>
		<th>廣告名稱</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>促銷折扣</th>
		<th>廣告說明</th>
		<th>廣告狀態</th>
		<th>廣告圖檔</th>
		<th>上架</th>
		<th>下架</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="promotionVO" items="${list}" >
		
		<tr style="line-height: 42px; text-align: center; border-bottom: 1px solid #aaa;">
			<td style=" width: 100px;">${promotionVO.promotion_no}</td>
			<td>${promotionVO.merchant_no}</td>
			<td style=" width: 100px;">${promotionVO.product_no}</td>
			<td>${promotionVO.promotion_name}</td>
			<td>${promotionVO.promotion_start}</td>
			<td>${promotionVO.promotion_end}</td>
			<td>${promotionVO.promotion_pr}</td>
			<td>${promotionVO.promotion_ps}</td>
			<td>
			<c:set var="promotion_status" value="${promotionVO.promotion_status}"/>
							<c:if test="${promotion_status == 'B1'}">
                				<%= "未審核"%>
								</c:if>
							<c:if test="${promotion_status == 'B2'}">
								<%= "上架"%>
							</c:if>
							<c:if test="${promotion_status == 'B3'}">
								<%= "下架"%>
							</c:if>
			</td>
			<td><img src="<%=request.getContextPath()%>/PromotionImageShow?promotion_no=${promotionVO.promotion_no}"  width="160vm"  height="120vm" ></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromotionServlet1" style="margin-bottom: 0px;">
			     <input class="btn btn-outline-secondary" type="submit" value="上架">
			     <input type="hidden" name="promotion_no"  value="${promotionVO.promotion_no}">
			     <input type="hidden" name="promotion_status"  value="B2">
			     <input type="hidden" name="product_no"  value="${promotionVO.product_no}">
			     <input type="hidden" name="product_pn"  value="D1">
				 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOnePromotionStatus_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromotionServlet1" style="margin-bottom: 0px;">
			     <input class="btn btn-outline-secondary" type="submit" value="下架">
			     <input type="hidden" name="promotion_no"  value="${promotionVO.promotion_no}">
			     <input type="hidden" name="promotion_status"  value="B3">
			     <input type="hidden" name="product_no"  value="${promotionVO.product_no}">
			     <input type="hidden" name="product_pn"  value="D0">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOnePromotionStatus_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</div>

</body>
</html>