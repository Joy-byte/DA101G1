<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<% 
 		List<ProductVO> list = (List<ProductVO>) request.getAttribute("get_OneStatus_Product"); //EmpServlet.java(Concroller), 存入req的merchantVO物件
 		pageContext.setAttribute("list",list);
%> 


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

.status{

flex-direction: colum;
margin-top:30px;
margin-right:15px;
margin-left: 15px;
margin-bottom: 30px;
background-color:#fff;
}

</style>

<style>
  table {
	width: auto;
	background-color: white;
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


<div class="content container"  style=" display: flex;flex-direction: column;" >
<table>
	<tr style="line-height: 30px; text-align: center; border-bottom: 1px solid #aaa;">
		<th>商品編號</th>
		<th>廠商編號</th>
		<th>商品名稱</th>
		<th>商品狀態</th>
		<th>商品價格</th>
		<th>商品種類</th>
		<th>廣告促銷狀態</th>
		<th>商品說明</th>
		<th>商品圖檔</th>
		<th>修改</th>
	</tr>
	<%@ include file="page/page1.file" %> 
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr style="line-height: 30px; text-align: center; border-bottom: 1px solid #aaa;" ${(productVO.product_no==param.product_no) ? 'bgcolor=#E0FFFF':''}>
			<td>${productVO.product_no}</td>
			<td>${productVO.merchant_no}</td>
			<td>${productVO.product_name}</td>
			<td>
			<c:set var="product_status" value="${productVO.product_status}"/>
			<c:if test="${product_status == 'C0'}">
            	<%= "下架"%>
			</c:if>
			<c:if test="${product_status == 'C1'}">
            	<%= "上架"%>
			</c:if>	
			</td>
			<td>${productVO.product_pr}</td>
			<td>${productVO.product_typ}</td> 
			<td>
			<c:set var="product_pn" value="${productVO.product_pn}"/>
			<c:if test="${product_pn == 'D0'}">
            	<%= "無"%>
			</c:if>
			<c:if test="${product_pn == 'D1'}">
            	<%= "有"%>
			</c:if>
			</td>
			<td>${productVO.product_ps}</td>
			<td>
			<img src="<%=request.getContextPath()%>/ProductImageShow?product_no=${productVO.product_no}" width="160vm"  height="120vm">
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" style="margin-bottom: 0px;">
			     <input class="btn btn-outline-secondary" type="submit" value="下架">
			     <input type="hidden" name="product_no"  value="${productVO.product_no}">
			     <input type="hidden" name="product_status"  value="C0">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="product_status_one"  value="one">
			     <input type="hidden" name="action"	value="getOneProductStatus_Update_Back"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>

</div>

</body>
</html>