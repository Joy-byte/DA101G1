<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.landscape.model.*"%>
<%@ page import="java.util.*"%>

<%
	MemberService ms = new MemberService();
	List<MemberVO> mlist = ms.getAll();
	int count = 0;

	for (MemberVO mlist2 : mlist) {
		count++;
	}
%>
<%
	LandscapeService ls = new LandscapeService();
	List<LandscapeVO> llist = ls.getAll();
	int count1 = 0;

	for (LandscapeVO llist2 : llist) {
		count1++;
	}
%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://aishek.github.io/jquery-animateNumber/javascripts/jquery.animateNumber.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title></title>

<style>
body {
	font-family: Microsoft JhengHei;
}

.content {
	flex-wrap: wrap;
	display: flex;
	background-color: #f0f0f0;
	margin-bottom: 50px;
	width: 100%;
}

.status {
	flex-direction: colum;
	text-align: center;
	height: 245px;
	width: 245px;
	margin-top: 30px;
	margin-right: 15px;
	margin-left: 30px;
	margin-bottom: 30px;
	background-color: #fff;
}

hr {
	margin-top: 7px;
	*margin: 0;
	border: 0;
	color: #fff;
	background-color: #fff;
	height: 3px;
	width: 50%;
}

.number {
	font-size: 38pt;
	color: #fff;
}

.photo {
	width: 80px;
	height: 80px;
}
</style>
</head>
<body>
	<div style="display: flex; font-family: Microsoft JhengHei;">
		<div>
			<jsp:include page="icon.jsp" flush="true" />
		</div>

		<div style="width: 100%">
			<jsp:include page="header.jsp" flush="true" />
			<div class="content" style="display: flex; flex-direction: column">
				<div style="display: flex;">
					<div class="status" style="background-color: #d1ecf1;">
						<img src="traveler.png" class="photo"><br> <span
							class="number"><%=count%></span>
						<hr>
						<h3 style="color: #fff">會員!</h3>
					</div>
					<div class="status" style="background-color: #c4e1e1;">
						<img src="camera.png" class="photo"><br> <span
							class="number"><p id="decimals1"></p></span>
						<hr>
						<h3 style="color: #fff">行程!</h3>
					</div>
					<div class="status" style="background-color: #ffd1a4;">
						<img src="hands.png" class="photo"><br> <span
							class="number"><p id="decimals2"></p></span>
						<hr>
						<h3 style="color: #fff">旅伴!</h3>
					</div>
					<div class="status" style="background-color: #cf9e9e;">
						<img
							src="<%=request.getContextPath()%>/back-end/portal/image/order.png"
							class="photo"><br> <span class="number"><p
								id="decimals3"></p></span>
						<hr>
						<h3 style="color: #fff">訂單!</h3>
					</div>
				</div>

				<div class="container" style="display:flex; margin-left:15px">
				      
						<table class="table table-striped" style="margin-right:50px;">
							<tbody>
								<tr>
									<td class=""><%=count1%>&nbsp個景點</td>
									<td class="">已登錄</td>
								</tr>
								<tr>

									<td>12&nbsp個行程</td>
									<td>在本月新增</td>


								</tr>
								<tr>
									<td>4&nbsp位會員</td>
									<td>正在進行遊戲</td>
								<tr>
									<th scope="row"></th>
									<td></td>


								</tr>
								<tr>
									<th scope="row"></th>
									<td></td>


								</tr>
								<tr>
									<th scope="row"></th>
									<td></td>
								</tr>
							</tbody>
						</table>
						<table class="table table-striped">
							<tbody>
								<tr>
									<td class="">4&nbsp位會員</td>
									<td class="">在本月找到旅伴</td>
								</tr>
								<tr>

									<td>1&nbsp筆訂單</td>
									<td>在本月新增</td>


								</tr>
								<tr>
									<td>1106個旅店</td>
									<td>已登錄</td>
								<tr>
									<th scope="row"></th>
									<td></td>


								</tr>
								<tr>
									<th scope="row"></th>
									<td></td>


								</tr>
								<tr>
									<th scope="row"></th>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
			</div>
		</div>
	</div>
	<script>
		$('#decimals1').animateNumber(
				{
					number : 24,
					numberStep : function(now, tween) {
						// see http://stackoverflow.com/a/14428340
						var formatted = now.toFixed(0).replace(
								/(\d)(?=(\d{3})+\.)/g, '$1,');
						$(tween.elem).text(formatted);
					}
				}, 2000);
		$('#decimals2').animateNumber(
				{
					number : 28,
					numberStep : function(now, tween) {
						// see http://stackoverflow.com/a/14428340
						var formatted = now.toFixed(0).replace(
								/(\d)(?=(\d{3})+\.)/g, '$1,');
						$(tween.elem).text(formatted);
					}
				}, 2000);
		$('#decimals3').animateNumber(
				{
					number : 32,
					numberStep : function(now, tween) {
						// see http://stackoverflow.com/a/14428340
						var formatted = now.toFixed(0).replace(
								/(\d)(?=(\d{3})+\.)/g, '$1,');
						$(tween.elem).text(formatted);
					}
				}, 2000);
	</script>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
