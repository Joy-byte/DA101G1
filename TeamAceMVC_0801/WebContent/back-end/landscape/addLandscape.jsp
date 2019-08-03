<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.landscape.model.*"%>

<%
	LandscapeVO landscapeVO = (LandscapeVO) request.getAttribute("landscapeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>景點資料新增 - addEmp.jsp</title>
<style>

</style>

</head>
<body>
	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/landscape/Landscapebar.jsp" flush="true" />
		</div>
		<div style="width:100%;">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />

			<div style="width:100%; height: 50px; background-color: #c4e1e1;">
				<h5 style="line-height: 50px; margin: 10px">資料新增:</h5>
			</div>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
           <div class="content container" style="background-color:#fff; width:auto;display:flex; justify-content:center">
			<FORM METHOD="post" ACTION="LandscapeServlet.do" name="form1">
				<table style="margin-top:50px">
					<tr>
						<td>景點名:</td>
						<td><input class="form-control" type="TEXT"
							name="landscape_name" size="45"
							value="<%=(landscapeVO == null) ? "中壢資策會" : landscapeVO.getLandscape_name()%>" /></td>
					</tr>
					<tr>
						<td>經度:</td>
						<td><input class="form-control" type="TEXT"
							name="landscape_lng" size="45"
							value="<%=(landscapeVO == null) ? "120.25" : landscapeVO.getLandscape_lng()%>" /></td>
					</tr>
					<tr>
						<td>緯度:</td>
						<td><input class="form-control" type="TEXT"
							name="landscape_lat" size="45"
							value="<%=(landscapeVO == null) ? "23.5" : landscapeVO.getLandscape_lat()%>" /></td>
					</tr>
					<tr>
						<td>景點狀態:</td>
						<td><input class="form-control" type="TEXT"
							name="landscape_status" size="45"
							value="<%=(landscapeVO == null) ? "L1" : landscapeVO.getLandscape_status()%>" /></td>
					</tr>
					<tr>
						<td>景點地址:</td>
						<td><input class="form-control" type="TEXT"
							name="landscape_add" size="45"
							value="<%=(landscapeVO == null) ? "桃園市" : landscapeVO.getLandscape_add()%>" /></td>
					</tr>
				</table>
				<br> <input type="hidden" name="action" value="insert">
				<input class="btn btn-outline-secondary" type="submit" value="送出新增">
			</FORM>
			</div>
		</div>
	</div>
</body>
</html>