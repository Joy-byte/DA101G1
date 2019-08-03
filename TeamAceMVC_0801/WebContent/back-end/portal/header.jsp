<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>

<title></title>

</head>
<body>
	<div class="admin"
		style="width:auto; top: 0px; height: 80px; background-color: #fcfcfc;  background-size: cover; display: flex; justify-content: flex-end; line-height: 80px;">
		<span style="position:absolute; right: 15%"> <c:if
				test="${administratorVO!=null}">
				<id class="administrator">Hi!
				${administratorVO.administrator_name} /<a href="#">µn¥X</a></id>
			</c:if> <c:if test="${administratorVO==null}">
				<id class="administrator"> <a
					href="<%=request.getContextPath()%>/back-end/member/loginforadmin.jsp">µn¤J</a></id>
			</c:if>
		</span>
	</div>
</body>
</html>