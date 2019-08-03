<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.guide.model.*"%>
<%@ page import="com.mem.model.*"%>


<%
	GuideService guideSvc = new GuideService();
	List<GuideVO> list = guideSvc.getAll();
	pageContext.setAttribute("list", list);

	//單一查詢
	GuideVO guideVO = (GuideVO) request.getAttribute("guide_VO");
%>
<jsp:useBean id="memSvc" scope="page"
	class="com.mem.model.MemberService" />

<!doctype html>
<html lang="en">
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

<title>GuideList</title>
<style type="text/css">
body {
	font-family: Microsoft JhengHei;
	letter-spacing: 2px;
}

.outer {
	height: 780px;
	width: 1000px;
	background-color: #fff;
	border-radius: 1%;
	margin-left:110px;
}

.title {
	font-size: 18px;
	line-height: 60px;
}

.content {
	margin-left: 70px;
	margin-right: 70px;
	margin-top: 0px;
}

.tab-content {
	height: 530px;
	width: 800px;
	font-size: 14px;
}

table {
	width: 800px;
	line-height: 36px;
}

tr {
	border-bottom: 1px solid #aaa;
}

.btn {
	background-color: dimgray;
	font-size: 12px;
	color: #fff;
}
</style>
</head>
<body onload="onload()">
<div class="realouter" style="display:flex; font-family: Microsoft JhengHei;">
	<div>
		<jsp:include page="/back-end/portal/icon.jsp" flush="true" />
	</div>
	<div class="outer" style="width:100%">
	<jsp:include page="/back-end/portal/header.jsp" flush="true" />
	        <div style="background-color:#ffd1a4; height:50px; width:100%; line-height:50px">
				<h5 style="line-height:50px; margin-left:10px">在地旅伴查詢</h5>
			</div>
		<div class="content container" style="width:830px">
			<ul class="nav nav-tabs" id="myTab" role="tablist" style="margin-top:30px">
				<li class="nav-item"><a class="nav-link" id="home-tab"
					data-toggle="tab" href="#home" role="tab" aria-controls="home"
					aria-selected="true">全部查詢</a></li>
				<li class="nav-item"><a class="nav-link " id="profile-tab"
					data-toggle="tab" href="#profile" role="tab"
					aria-controls="profile" aria-selected="false">單一查詢</a></li>
			</ul>

			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade" id="home" role="tabpanel"
					aria-labelledby="home-tab">
					<table class="list-all-table">
						<tr style="line-height: 42px; text-align: center">
							<td>旅伴編號</td>
							<td>姓名</td>
							<td>Email</td>
							<td>伴遊區域</td>
							<td>停權狀態</td>
							<td>更改狀態</td>
						</tr>
						<c:forEach var="guideVO" items="${list}">
							<tr>
								<td style="text-align: center">${guideVO.guide_no}</td>
								<td style="width: 90px; text-align: center">${memSvc.getOneMember(guideVO.getMem_no()).mem_name}</td>
								<td style="width: 150px; padding-left: 20px">${memSvc.getOneMember(guideVO.getMem_no()).mem_email}</td>
								<td style="text-align: center">${guideVO.guide_area}</td>
								<td style="text-align: center"><c:choose>
										<c:when test="${(guideVO.guide_status).equals('GN1')}">
									正常							         
							    </c:when>
										<c:when test="${(guideVO.guide_status).equals('GN2')}">
									停權							         
							    </c:when>
									</c:choose></td>
								<td style="text-align: center"><c:choose>
										<c:when test="${(guideVO.guide_status).equals('GN1')}">
											<FORM METHOD="post" ACTION="guide.do">
												<input type="hidden" name="guide_no"
													value="${guideVO.guide_no}"> <input type="hidden"
													name="action" value="getOne_for_update"> <input
													type="submit" class="btn" name="type" value="停權">
											</FORM>
										</c:when>
										<c:when test="${(guideVO.guide_status).equals('GN2')}">
											<FORM METHOD="post" ACTION="guide.do">
												<input type="hidden" name="guide_no"
													value="${guideVO.guide_no}"> <input type="hidden"
													name="action" value="getOne_for_update"> <input
													type="submit" class="btn" name="type" value="復權">
											</FORM>
										</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- 單一查詢 -->
				<div class="tab-pane fade" id="profile" role="tabpanel"
					aria-labelledby="profile-tab">
					<FORM METHOD="post" ACTION="guide.do">
						<b>輸入旅伴編號:</b> <input type="text" name="guide_no"> <input
							type="hidden" name="action" value="getOne_for_display">
						&nbsp
						<button type="submit" class="btn">送出</button>
					</FORM>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<c:forEach var="msg" items="${errorMsgs}">
							<font style="color: red; font-size: 12px;">${msg}&nbsp</font>
						</c:forEach>
					</c:if>
					<br>
					<table class="list-one-table">
						<tr style="line-height: 42px; text-align: center">
							<td>旅伴編號</td>
							<td>姓名</td>
							<td>Email</td>
							<td>伴遊區域</td>
							<td>停權狀態</td>
							<td>更改狀態</td>
						</tr>

						<tr>
							<td style="text-align: center">${requestScope.guideVO.guide_no}</td>
							<td style="width: 90px; text-align: center">${memSvc.getOneMember(requestScope.guideVO.getMem_no()).mem_name}</td>
							<td style="width: 150px; padding-left: 20px">${memSvc.getOneMember(requestScope.guideVO.getMem_no()).mem_email}</td>
							<td style="text-align: center">${requestScope.guideVO.guide_area}</td>
							<td style="text-align: center"><c:choose>
									<c:when
										test="${(requestScope.guideVO.guide_status).equals('GN1')}">
									正常							         
							    </c:when>
									<c:when
										test="${(requestScope.guideVO.guide_status).equals('GN2')}">
									停權							         
							    </c:when>
								</c:choose></td>
							<td style="text-align: center"><c:choose>
									<c:when
										test="${(requestScope.guideVO.guide_status).equals('GN1')}">
										<FORM METHOD="post" ACTION="guide.do">
											<input type="hidden" name="guide_no"
												value="${requestScope.guideVO.guide_no}"> <input
												type="hidden" name="action" value="getOne_for_update">
											<input type="hidden" name="from" value="listOne"> <input
												type="submit" class="btn" name="type" value="停權">
										</FORM>
									</c:when>
									<c:when
										test="${(requestScope.guideVO.guide_status).equals('GN2')}">
										<FORM METHOD="post" ACTION="guide.do">
											<input type="hidden" name="guide_no"
												value="${requestScope.guideVO.guide_no}"> <input
												type="hidden" name="action" value="getOne_for_update">
											<input type="hidden" name="from" value="listOne"> <input
												type="submit" class="btn" name="type" value="復權">
										</FORM>
									</c:when>
								</c:choose></td>
						</tr>

					</table>
				</div>

			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		function onload() {
			if ('${navLink}' == 'listOne') {
				document.getElementById("home-tab").setAttribute("class",
						"nav-link");
				document.getElementById("profile-tab").setAttribute("class",
						"nav-link active");
				document.getElementById("home").setAttribute("class",
						"tab-pane fade");
				document.getElementById("profile").setAttribute("class",
						"tab-pane fade show active");
			}
			if ('${navLink}' == '') {
				document.getElementById("home-tab").setAttribute("class",
						"nav-link active");
				document.getElementById("profile-tab").setAttribute("class",
						"nav-link");
				document.getElementById("home").setAttribute("class",
						"tab-pane fade show active");
				document.getElementById("profile").setAttribute("class",
						"tab-pane fade");
			}
		}
	</script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
</body>
</html>