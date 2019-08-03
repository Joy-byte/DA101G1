<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Order_list_select_page: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Order_list_select_page: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Order_list_select_page: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/order_list/listAllOrder_list.jsp'>List</a> all Order_list_select_page.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_listServlet1" >
        <b>輸入訂單編號 (如OR00001):</b>
        <input type="text" name="order_no">
        <input type="text" name="product_no">
        <input type="hidden" name="action" value="getOne_For_Order_list">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="order_listSvc" scope="page" class="com.order_list.model.Order_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_listServlet1" >
       <b>選擇訂單編號:</b>
       <select size="1" name="order_no">
         <c:forEach var="order_listVO" items="${order_listSvc.all}" > 
          <option value="${order_listVO.order_no}">${order_listVO.order_no}
         </c:forEach>    
       </select>
       <select size="1" name="product_no">
       <c:forEach var="order_listVO" items="${order_listSvc.all}" > 
          <option value="${order_listVO.product_no}">${order_listVO.product_no}
       </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getOne_For_Order_list">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_listServlet1" >
       <b>選擇產品:</b>
       <select size="1" name="order_no">
         <c:forEach var="order_listVO" items="${order_listSvc.all}" > 
          <option value="${order_listVO.order_no}">${order_listVO.product_no}
         </c:forEach>   
       </select>
       <select size="1" name="product_no">
         <c:forEach var="order_listVO" items="${order_listSvc.all}" > 
          <option value="${order_listVO.product_no}">${order_listVO.order_product_pr}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Order_list">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>管理</h3>

<ul>
  <li><a href='addOrder_list.jsp'>Add</a> a new Order_list.</li>
</ul>

</body>
</html>