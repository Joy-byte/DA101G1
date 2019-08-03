<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Order_detail_select_page: Home</title>

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
   <tr><td><h3>IBM Order_detail_select_page: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Order_detail_select_page: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/front-end/order_detail/listAllOrder_detail.jsp'>List</a> all Order_detail_select_page.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>//Order_detailServlet1" >
        <b>輸入訂單編號 (如OR00001):</b>
        <input type="text" name="order_no">
        <input type="hidden" name="action" value="getOne_For_Order_detail">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="order_detailSvc" scope="page" class="com.order_detail.model.Order_detailService" />
   <jsp:useBean id="merchantSvc" scope="page" class="com.merchant.model.MerchantService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>//Order_detailServlet1" >
       <b>選擇訂單編號:</b>
       <select size="1" name="order_no">
         <c:forEach var="order_detailVO" items="${order_detailSvc.all}" > 
          <option value="${order_detailVO.order_no}">${order_detailVO.order_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Order_detail">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>//Order_detailServlet1" >
       <b>選擇產品:</b>
       <select size="1" name="order_no">
         <c:forEach var="order_detailVO" items="${order_detailSvc.all}" > 
          <option value="${order_detailVO.order_no}">${order_detailVO.mem_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Order_detail">
       <input type="submit" value="送出">
     </FORM>
  </li>
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>//Order_detailServlet1" >
       <b>選擇月份:</b>
         <select size="1" name="merchant_no">
          	<c:forEach var="merchantVO" items="${merchantSvc.all}" > 
          	<option value="${merchantVO.merchant_no}">${merchantVO.merchant_no}
         	</c:forEach>   
         </select>
       	 <select name="month">
　				<option value="01">一月</option>
　				<option value="02">二月</option>
　				<option value="03">三月</option>
				<option value="04">四月</option>
				<option value="05">五月</option>
				<option value="06">六月</option>
				<option value="07">七月</option>
				<option value="08">八月</option>
				<option value="09">九月</option>
				<option value="10">十月</option>
				<option value="11">十一月</option>
				<option value="12">十二月</option>
		</select>
       <input type="hidden" name="action" value="getOneMonth_One_Merchant">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  
  
   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_detailServlet1" >
     	<b>輸入會員編號 (如MB00002):</b>
        <input type="text" name="mem_no">
        <b>選擇訂單狀態:</b>
    		<select name="order_status">
　				<option value="O1">撿貨</option>
　				<option value="O2">已出貨</option>
				<option value="O3">取消訂單</option>
				<option value="O4">退貨</option>
			</select>
       <b>選擇日期:</b>
                    開始日期:<input name="order_time_start" id="f_date1" type="date"><br>
		結束日期:<input name="order_time_end" id="f_date2" type="date"><br>
		<b>選擇金額:</b>
		大於多少<input type="TEXT" name="order_amosum_start" size="45"  value="" /><br>
		小於多少<input type="TEXT" name="order_amosum_end" size="45"  value="" /><br>
         
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listorderDetail_ByCompositeQuery">
     </FORM>
  </li>
  

  
</ul>


<h3>管理</h3>

<ul>
  <li><a href='addOrder_detail.jsp'>Add</a> a new Order_detail.</li>
</ul>

</body>
</html>