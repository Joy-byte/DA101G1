<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.order_list.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Order_listVO order_listVO = (Order_listVO) request.getAttribute("order_listVO"); //Order_listServlet.java(Concroller), 存入req的empVO物件
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

.content{
display:flex;
width:100%;
margin:0;
padding:0;
background-color:	#f0f0f0;
background-size:cover;
margin-bottom: 50px;
}
.status{

flex-direction: colum;
text-align: center;
margin-top:30px;
margin-right:15px;
margin-left: 15px;
margin-bottom: 30px;
background-color:#fff;
}
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

<style>
  #table-1 {
    border: 2px solid black;
    text-align: center;
    border-collapse:collapse;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<div class="logo">
<img src="<%=request.getContextPath()%>/back-end/order_list/icon.jpeg" id="logo">

<ul class="list-group list-group-flush ">
  <li class="list-group-item"><a href='<%=request.getContextPath()%>/back-end/order_detail/portal.jsp'>查詢</a></li>
  <li class="list-group-item"><a href='<%=request.getContextPath()%>/back-end/order_detail/listAllOrder_detail.jsp'>訂單全部</a></li>
  <li class="list-group-item"><a href='<%=request.getContextPath()%>/back-end/Index/portal.jsp'>回首頁</a></li>
</ul>
</div>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div class="content container">



<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneOrder_list.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/order_list/order_list_select_page.jsp"><img src="<%=request.getContextPath()%>/order_list/merchant/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>訂單中商品金額</th>
		<th>訂購數量</th>
	</tr>
	<tr>
		<td><%=order_listVO.getOrder_no()%></td>
		<td><%=order_listVO.getProduct_no()%></td>
		<td><%=order_listVO.getOrder_product_pr()%></td>
		<td><%=order_listVO.getOrder_quan()%></td>
	</tr>
</table>

</div>

</body>
</html>