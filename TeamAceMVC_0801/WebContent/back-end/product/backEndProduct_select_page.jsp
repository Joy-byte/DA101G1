<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

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
  <li><a href='listAllProduct.jsp'>List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" >
        <b>輸入商品編號 (如PR00001):</b>
        <input type="text" name="product_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

   <jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
   
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" >
       <b>選擇商品編號:</b>
       <select size="1" name="product_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.product_no}">${productVO.product_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" >
       <b>選擇廠商編號:</b>
       <select size="1" name="product_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.product_no}">${productVO.merchant_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" >
       <b>選擇商品狀態:</b>
    		<select name="product_status">
　				<option value="C0">下架</option>
　				<option value="C1">上架</option>
			</select>
       <input type="hidden" name="action" value="get_OneStatus_Product">
       <input type="submit" value="送出">
     </FORM>
  </li>
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductServlet1" >
       <b>選擇商品狀態:</b>
    		<select name="product_status">
　				<option value="C0">下架</option>
　				<option value="C1">上架</option>
			</select>
	   <b>選擇廣告狀態:</b>
	   		<select name="product_pn">
　				<option value="D0">沒有廣告促銷狀態</option>
　				<option value="D1">有廣告促銷狀態</option>
			</select>
       <input type="hidden" name="action" value="get_TwoStatus_Product">
       <input type="submit" value="送出">
     </FORM>
  </li>

</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addProduct.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>