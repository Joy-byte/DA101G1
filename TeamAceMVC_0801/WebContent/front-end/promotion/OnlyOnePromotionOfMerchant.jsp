<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<%-- <%=session.getAttribute("merchant_no")%> --%>
  
<%  	
	PromotionService promotionSvc = new PromotionService();
    List<PromotionVO> list = promotionSvc.getOneMerchantNo((String)(session.getAttribute("merchant_no")));
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>所有廣告資料 - listAllPromotion.jsp</title>

<style>
  table {
	width: 918px;
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

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table style="border-top:3px #4682B4 solid;border-bottom:3px #4682B4 solid;" >
	<tr style="border-top:3px #4682B4 solid;border-bottom:3px #4682B4 solid;">
		<th>商品編號</th>
		<th style=" width: 100px;">廣告名稱</th>
		<th style=" width: 100px;">開始日期</th>
		<th style=" width: 100px;">結束日期</th>
		<th>促銷折扣</th>
		<th style=" width: 100px;">廣告說明</th>
		<th>廣告狀態</th>
		<th>廣告圖檔</th>
		<th>修改</th>
		<th>下架</th>
	</tr>
	<%@ include file="pages/page1.file" %>   
	<c:forEach var="promotionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr ${(promotionVO.promotion_no==param.promotion_no) ? 'bgcolor=#E0FFFF':''}>
			<td>${promotionVO.product_no}</td>
			<td>${promotionVO.promotion_name}</td>
			<td>${promotionVO.promotion_start}</td>
			<td>${promotionVO.promotion_end}</td>
			<td>${promotionVO.promotion_pr}</td>
<%-- 			<td>${promotionVO.promotion_ps.substring(0, 20)}</td> --%>
			<td><A href="<%=request.getContextPath()%>/PromotionServlet1?promotion_no=${promotionVO.promotion_no}&action=getOne_From04">${promotionVO.promotion_ps}</a></td>
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
			<td><img src="<%=request.getContextPath()%>/PromotionImageShow?promotion_no=${promotionVO.promotion_no}"  width="300vm"  height="200vm" ></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromotionServlet1" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="promotion_no"  value="${promotionVO.promotion_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PromotionServlet1" style="margin-bottom: 0px;">
			     <input type="submit" value="下架">
			     <input type="hidden" name="promotion_no"  value="${promotionVO.promotion_no}">
			     <input type="hidden" name="promotion_status"  value="B3">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="product_no"  value="${promotionVO.product_no}">
			     <input type="hidden" name="product_pn"  value="D0">
			     <input type="hidden" name="action"	value="getOnePromotionStatus_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>