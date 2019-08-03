<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>TeamAce_Backend</title>

<style>
body {
	font-family: Microsoft JhengHei;
}

.landno {
	margin: 10px;
	display: flex;
	flex-direction: column;
}

.btn {
	margin: 10px;
	width: 100px;
}

.subtitle {
	margin: 10px;
}
</style>

</head>
<body>
	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="/back-end/landscape/Landscapebar.jsp" flush="true" />
		</div>
		<div style="width: 100%;">
			<jsp:include page="/back-end/portal/header.jsp" flush="true" />
			<div style="width: 100%; height: 50px; background-color: #c4e1e1;">
				<h5 style="line-height: 50px; margin: 10px">景點查詢:</h5>
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

			<div class="container" style="display: flex;">

				<div class="col-3" style="margin: 10px">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do">
						<div class="subtitle">
							<b>輸入景點編號:</b>
						</div>
						<input class="form-control" type="text" name="landscape_no">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>

				<jsp:useBean id="landscapeSvc" scope="page"
					class="com.landscape.model.LandscapeService" />

				<div class="col-3 landno">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do">
						<div class="subtitle">
							<b>選擇景點編號:</b>
						</div>
						<div>
							<select class="form-control" size="1" name="landscape_no">
								<c:forEach var="landscapeVO" items="${landscapeSvc.all}">
									<option value="${landscapeVO.landscape_no}">${landscapeVO.landscape_no}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
						</div>
						<div>
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</div>
					</FORM>
				</div>

				<div class="col-3" style="margin: 10px">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/landscape/LandscapeServlet.do">
						<div class="subtitle">
							<b>選擇景點名:</b>
						</div>

						<select class="form-control" size="1" name="landscape_no">
							<c:forEach var="landscapeVO" items="${landscapeSvc.all}">
								<option value="${landscapeVO.landscape_no}">${landscapeVO.landscape_name}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>
			</div>
			<div>
					<%
						if (request.getAttribute("landscapeVO") != null) {
					%>
					<jsp:include page="landscapeOne.jsp" flush="true" />
					<%
						}
					%>
				</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script language="JavaScript">
		function toggleVisibility() {
			var menuf = document.getElementById("menuf");
			if (menuf.style.visibility == "hidden") {
				menuf.style.visibility = "visible";
			} else {
				menuf.style.visibility = "hidden";
			}
		}
	</script>
</body>
</html>