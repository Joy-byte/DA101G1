<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productreport.model.*"%>
<%
ProductreportVO productreportVO = (ProductreportVO) request.getAttribute("productreportVO"); //ProductreportServlet.java (Concroller) 存入req的productreportVO物件 (包括幫忙取出的productreportVO, 也包括輸入資料錯誤時的productreportVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>儲值資料修改 - update_Productreport_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>儲值資料新增 - addProductreport.jsp</h3></td><td>
		 <h4><a href="productreport_select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/productreport/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductreportServlet1" name="form1">
<table>
	<tr>
		<td>檢舉編號:<font color=red><b>*</b></font></td>
		<td><%=productreportVO.getProductreport_no()%></td>
	</tr>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="product_no" size="45" value="<%=productreportVO.getProduct_no()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉說明:</td>
		<td><input type="TEXT" name="reportcon_no" size="45" value="<%=productreportVO.getReportcon_no()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉狀態:</td>
		<td><input type="TEXT" name="reportcon_status" size="45" value="<%=productreportVO.getReportcon_status()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="productreport_no" value="<%=productreportVO.getProductreport_no()%>">
<input type="submit" value="送出修改"></FORM>


</body>
</html>