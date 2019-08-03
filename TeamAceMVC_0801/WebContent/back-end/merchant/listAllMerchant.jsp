<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchant.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MerchantService merchantSvc = new MerchantService();
    List<MerchantVO> list = merchantSvc.getAll();
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


<div class="content" style=" display: flex;flex-direction: column; ">
<table style="margin-top:50px">
	<tr style="line-height: 30px; text-align: center; border-bottom: 1px solid #aaa;">
		<th>廠商編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>名稱</th>
		<th>負責人姓名</th>
		<th>地址</th>
		<th>電話</th>
		<th>電子郵件</th>
		<th>廠商狀態</th>
		<th style=" width:350px;">廠商說明</th>
		<th>圖檔</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page/page1.file" %> 
	<c:forEach var="merchantVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr style="line-height: 30px; text-align: center; border-bottom: 1px solid #aaa;" ${(merchantVO.merchant_no==param.merchant_no) ? 'bgcolor=#E0FFFF':''}>
			<td>${merchantVO.merchant_no}</td>
			<td>${merchantVO.merchant_id}</td>
			<td>${merchantVO.merchant_pass}</td>
			<td>${merchantVO.merchant_name}</td>
			<td>${merchantVO.merchant_pm}</td>
			<td>${merchantVO.merchant_add}</td> 
			<td>${merchantVO.merchant_tel}</td>
			<td>${merchantVO.merchant_email}</td>
			<td>
			<c:set var="merchant_status" value="${merchantVO.merchant_status}"/>
							<c:if test="${merchant_status == 'A1'}">
                				<%= "未審核"%>
								</c:if>
							<c:if test="${merchant_status == 'A2'}">
								<%= "正常"%>
							</c:if>
							<c:if test="${merchant_status == 'A3'}">
								<%= "凍結"%>
							</c:if>
			</td>
			<td>${merchantVO.merchant_ps}</td>
  	    	<td><img src="<%=request.getContextPath()%>/MerchantImageShow?merchant_no=${merchantVO.merchant_no}" width="160vm"  height="120vm"></td> 
  	    	


			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MerchantServlet1" style="margin-bottom: 0px;">
			     <input class="btn btn-outline-secondary" type="submit" value="正常">
			     <input type="hidden" name="merchant_no"  value="${merchantVO.merchant_no}">
			     <input type="hidden" name="merchant_status"  value="A2">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOneMerchantStatus_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MerchantServlet1" style="margin-bottom: 0px;">
			     <input class="btn btn-outline-secondary" type="submit" value="凍結">
			     <input type="hidden" name="merchant_no"  value="${merchantVO.merchant_no}">
			     <input type="hidden" name="merchant_status"  value="A3">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOneMerchantStatus_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page/page2.file" %>

</div>

</body>
</html>