<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.landscape.model.*"%>

<%
	LandscapeVO landscapeVO = (LandscapeVO) request.getAttribute("landscapeVO"); //EmpServlet.java (Concroller) 存入req的landscapeVO物件 (包括幫忙取出的landscapeVO, 也包括輸入資料錯誤時的landscapeVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_emp_input.jsp</title>

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
	width: auto;
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
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/landscape/Landscapebar.jsp" flush="true" />
		</div>
		<diV style="width: 100%">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />

			<div
				style="background-color: #c4e1e1; height: 50px; width: 100%; line-height: 50px">
				<strong style="margin-left: 20px;">所有景點資料</strong>
			</div>

			<FORM METHOD="post" ACTION="LandscapeServlet.do" name="form1">
				<div style="display: flex;justify-content: center;">
					<table style="margin-top:50px; margin-bottom:30px">
						<tr>
							<td>景點編號:<font color=red></font></td>
							<td><%=landscapeVO.getLandscape_no()%></td>
						</tr>
						<tr>
							<td>景點名:</td>
							<td><input class="form-control" type="TEXT"
								name="landscape_name" size="45"
								value="<%=landscapeVO.getLandscape_name()%>" /></td>
						</tr>
						<tr>
							<td>經度:</td>
							<td><input class="form-control" type="TEXT"
								name="landscape_lng" size="45"
								value="<%=landscapeVO.getLandscape_lng()%>" /></td>
						</tr>
						<tr>
							<td>緯度:</td>
							<td><input class="form-control" type="TEXT"
								name="landscape_lat" size="45"
								value="<%=landscapeVO.getLandscape_lat()%>" /></td>
						</tr>
						<tr>
							<td>景點狀態:</td>
							<td><input class="form-control" type="TEXT"
								name="landscape_status" size="45"
								value="<%=landscapeVO.getLandscape_status()%>" /></td>
						</tr>
						<tr>
							<td>景點地址:</td>
							<td><input class="form-control" type="TEXT"
								name="landscape_add" size="45"
								value="<%=landscapeVO.getLandscape_add()%>" /></td>
						</tr>


					</table>                 
				</div>
				<div style="text-align:center">
				 	<input type="hidden" name="action" value="update">
						<input type="hidden" name="landscape_no"
					value="<%=landscapeVO.getLandscape_no()%>"> 			
			  		<input class="btn btn-outline-secondary" type="submit" value="送出修改">
			   </div>
			</FORM>
		</div>
	</diV>

</body>










</html>