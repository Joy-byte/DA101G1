<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="memSvc" scope="page"
	class="com.mem.model.MemberService" />
<jsp:useBean id="guideTourSvc" scope="page"
	class="com.guidetour.model.GuideTourService" />
<jsp:useBean id="guideReportSvc" scope="page"
	class="com.guidereport.model.GuideReportService" />

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
	width: 1100px;
	background-color: #fff;
	position: absolute;
	top: 42%;
	left: 50%;
	margin: -390px -550px;
	border-radius: 1%;
}

.title {
	font-size: 18px;
	line-height: 60px;
}

.content {
	margin-left: 70px;
	margin-right: 70px;
	margin-top: 30px;
}

.tab-content {
	height: 530px;
	width: 960px;
	position: absolute;
	top: 70%;
	left: 50%;
	margin: -220px -480px;
	font-size: 14px;
}

table {
	width: 960px;
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
	<div class="logo" style="margin-right: 30px;">
		<div>
			<img src="<%=request.getContextPath()%>/back-end/portal/icon.jpeg"
				id="logo"
				style="width: 100px; height: 100px; margin-left: 70px; margin-top: 30px; margin-bottom: 30px;">
		</div>
		<div>
			<ul class="list-group list-group-flush"
				style="margin-left: 30px; width: 200px; text-align: center;">
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href="<%=request.getContextPath()%>/back-end/guidereport/GuideReport.jsp">旅伴檢舉</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href="<%=request.getContextPath()%>/back-end/productreport/portal.jsp">商品檢舉</a></li>
				<li class="list-group-item"><a
					style="text-decoration: none; color: black"
					href='<%=request.getContextPath()%>/back-end/portal/portal.jsp'>回首頁</a></li>
			</ul>
		</div>
	</div>
	<div class="outer" style="width: 79%">
		<jsp:include page="/back-end/portal/header.jsp" flush="true" />
		<div
			style="background-color: #eee8aa; height: 50px; width: 100%; line-height: 50px">
			<h5 style="line-height: 50px; margin-left: 20px">查詢商品檢舉</h5>
		</div>
		<div class="content">
			<font class="title">伴遊檢舉列表</font>
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link" id="home-tab"
					data-toggle="tab" href="#home" role="tab" aria-controls="home"
					aria-selected="true">未審核</a></li>
				<li class="nav-item"><a class="nav-link " id="profile-tab"
					data-toggle="tab" href="#profile" role="tab"
					aria-controls="profile" aria-selected="false">已審核</a></li>
			</ul>
			<!-- 未審核 -->
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade" id="home" role="tabpanel"
					aria-labelledby="home-tab">
					<table class="list-all-table">
						<tr style="line-height: 42px; text-align: center">
							<td>檢舉編號</td>
							<td>檢舉人</td>
							<td>檢舉人會員編號</td>
							<td>旅伴編號</td>
							<td>伴遊行程</td>
							<td style="width: 260px;">檢舉原因</td>
							<td style="width: 80px;">審核</td>
						</tr>
						<c:forEach var="guideReportVO"
							items="${guideReportSvc.getNotFinish()}" varStatus="status">
							<tr>
								<td style="text-align: center">${guideReportVO.guideRep_no}</td>
								<td style="width: 90px; text-align: center">${memSvc.getOneMember(guideReportVO.mem_no).mem_name}</td>
								<td style="text-align: center">${guideReportVO.mem_no}</td>
								<td style="text-align: center">${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guide_no}</td>
								<td style="text-align: center">
									<form id="form-tour${status.index}"
										action="../../front-end/guidetour/GuideTourDetail.jsp">
										<!-- 行程名 -->
										<input type="hidden" name="guideTour_no"
											value="${guideReportVO.guideTour_no}"> <input
											type="hidden" name="guide_no"
											value="${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guide_no}">
										<a href="#"
											onclick="document.getElementById('form-tour${status.index}').submit()">【${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guideTour_name}】</a>
									</form>
								</td>
								<td
									style="text-align: center; line-height: 20px !important; background: #eee; padding: 0 10px">${guideReportVO.guideRep_reason}</td>
								<td style="display: flex">
									<FORM METHOD="post" ACTION="GuideReportServlet">
										<input type="hidden" name="guideRep_no"
											value="${guideReportVO.guideRep_no}"> <input
											type="hidden" name="action" value="getOne_for_update">
										<input type="hidden" name="type" value="yes"> <input
											type="submit" class="btn" name="type" value="通過"
											style="margin: 0 10px 0 10px;">
									</FORM>
									<FORM METHOD="post" ACTION="GuideReportServlet">
										<input type="hidden" name="guideRep_no"
											value="${guideReportVO.guideRep_no}"> <input
											type="hidden" name="action" value="getOne_for_update">
										<input type="hidden" name="type" value="no"> <input
											type="submit" class="btn" name="type" value="不通過">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- 已審核 -->
				<div class="tab-pane fade" id="profile" role="tabpanel"
					aria-labelledby="profile-tab">
					<table class="list-all-table">
						<tr style="line-height: 42px; text-align: center">
							<td>檢舉編號</td>
							<td>檢舉人</td>
							<td>檢舉人會員編號</td>
							<td>旅伴編號</td>
							<td>伴遊行程</td>
							<td style="width: 260px;">檢舉原因</td>
							<td>審核結果</td>
						</tr>
						<c:forEach var="guideReportVO"
							items="${guideReportSvc.getFinish()}" varStatus="status">
							<tr>
								<td style="text-align: center">${guideReportVO.guideRep_no}</td>
								<td style="width: 90px; text-align: center">${memSvc.getOneMember(guideReportVO.mem_no).mem_name}</td>
								<td style="text-align: center">${guideReportVO.mem_no}</td>
								<td style="text-align: center">${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guide_no}</td>
								<td style="text-align: center">
									<form id="form-finish${status.index}"
										action="../../front-end/guidetour/GuideTourDetail.jsp">
										<!-- 行程名 -->
										<input type="hidden" name="guideTour_no"
											value="${guideReportVO.guideTour_no}"> <input
											type="hidden" name="guide_no"
											value="${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guide_no}">
										<a href="#"
											onclick="document.getElementById('form-finish${status.index}').submit()">【${guideTourSvc.getOneGuideTour(guideReportVO.guideTour_no).guideTour_name}】</a>
									</form>
								</td>
								<td
									style="text-align: center; line-height: 20px !important; background: #eee; padding: 0 10px">${guideReportVO.guideRep_reason}</td>
								<td style="text-align: center;"><c:choose>
										<c:when
											test="${(guideReportVO.guideRep_status).equals('GR2')}">
									通過
							    </c:when>
										<c:when
											test="${(guideReportVO.guideRep_status).equals('GR3')}">
									未通過
							    </c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onload() {
			// 			if('${navLink}'=='listOne'){
			// 				document.getElementById("home-tab").setAttribute("class","nav-link");
			// 				document.getElementById("profile-tab").setAttribute("class","nav-link active");
			// 				document.getElementById("home").setAttribute("class","tab-pane fade");
			// 				document.getElementById("profile").setAttribute("class","tab-pane fade show active");
			// 			}
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